package com.healthcare.hospital;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.UUID;
import java.util.Date;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DiagnosticsClient diagnosticsClient;

    public Appointment schedule(Appointment appointment) {
        appointment.setStatus(Appointment.AppointmentStatus.SCHEDULED);
        // Assign queue number (simplified: max +1 for the day)
        Integer maxQueue = appointmentRepository.findMaxQueueByDoctorAndDate(appointment.getDoctorId(), appointment.getDateTime());
        appointment.setQueueNumber(maxQueue != null ? maxQueue + 1 : 1);
        appointment.setEstimatedArrivalTime(calculateETA(appointment.getDateTime(), appointment.getQueueNumber()));
        appointment.setNotificationSent(false);
        appointment.setCheckedIn(false);
        Appointment saved = appointmentRepository.save(appointment);
        // Integrate diagnostics
        List<DiagnosticsClient.LabResultSummary> diagnostics = diagnosticsClient.getLabResultsByPatient(appointment.getPatientId());
        // Could add to response, but for now just log
        return saved;
    }

    public Appointment bookSlot(BookSlotRequest request) {
        Appointment appointment = new Appointment();
        appointment.setPatientId(request.getPatientId());
        appointment.setDoctorId(request.getDoctorId());
        appointment.setDateTime(request.getDateTime());
        appointment.setStatus(Appointment.AppointmentStatus.WAITING);
        return schedule(appointment);
    }

    public QueueStatus getQueueStatus(UUID appointmentId) {
        Appointment appointment = getById(appointmentId);
        if (appointment == null) return null;
        Integer currentServing = appointmentRepository.findMinQueueByDoctorAndDate(appointment.getDoctorId(), appointment.getDateTime());
        return new QueueStatus(appointment.getQueueNumber(), currentServing, appointment.getEstimatedArrivalTime());
    }

    public boolean checkIn(UUID appointmentId) {
        Appointment appointment = getById(appointmentId);
        if (appointment != null) {
            appointment.setStatus(Appointment.AppointmentStatus.ARRIVED);
            appointment.setCheckedIn(true);
            appointmentRepository.save(appointment);
            return true;
        }
        return false;
    }

    private Date calculateETA(Date appointmentTime, Integer queueNumber) {
        // Simplified: assume 15 min per appointment
        long etaMillis = appointmentTime.getTime() + (queueNumber - 1) * 15 * 60 * 1000;
        return new Date(etaMillis);
    }

    public Appointment reschedule(UUID id, Appointment updatedAppointment) {
        Appointment appointment = appointmentRepository.findById(id).orElse(null);
        if (appointment != null) {
            appointment.setDateTime(updatedAppointment.getDateTime());
            appointment.setStatus(Appointment.AppointmentStatus.RESCHEDULED);
            appointment.setNotes(updatedAppointment.getNotes());
            Appointment saved = appointmentRepository.save(appointment);
            // Create notification for patient
            String message = "Your appointment has been rescheduled to " + saved.getDateTime().toString();
            notificationService.createNotification(saved.getPatientId(), message, Notification.NotificationType.APPOINTMENT_REMINDER);
            return saved;
        }
        return null;
    }

    public boolean cancel(UUID id) {
        Appointment appointment = appointmentRepository.findById(id).orElse(null);
        if (appointment != null) {
            appointment.setStatus(Appointment.AppointmentStatus.CANCELLED);
            appointmentRepository.save(appointment);
            // Create notification for patient
            String message = "Your appointment has been cancelled.";
            notificationService.createNotification(appointment.getPatientId(), message, Notification.NotificationType.APPOINTMENT_REMINDER);
            return true;
        }
        return false;
    }

    public Appointment getById(UUID id) {
        return appointmentRepository.findById(id).orElse(null);
    }

    public List<Appointment> getByPatientId(UUID patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

    public List<Appointment> getByDoctorId(UUID doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }

    public List<Appointment> getAll() {
        return appointmentRepository.findAll();
    }

    // Inner classes for requests/responses
    public static class BookSlotRequest {
        private UUID patientId;
        private UUID doctorId;
        private Date dateTime;

        // getters/setters
        public UUID getPatientId() { return patientId; }
        public void setPatientId(UUID patientId) { this.patientId = patientId; }
        public UUID getDoctorId() { return doctorId; }
        public void setDoctorId(UUID doctorId) { this.doctorId = doctorId; }
        public Date getDateTime() { return dateTime; }
        public void setDateTime(Date dateTime) { this.dateTime = dateTime; }
    }

    public static class QueueStatus {
        private Integer yourQueue;
        private Integer currentServing;
        private Date estimatedTime;

        public QueueStatus(Integer yourQueue, Integer currentServing, Date estimatedTime) {
            this.yourQueue = yourQueue;
            this.currentServing = currentServing;
            this.estimatedTime = estimatedTime;
        }

        // getters
        public Integer getYourQueue() { return yourQueue; }
        public Integer getCurrentServing() { return currentServing; }
        public Date getEstimatedTime() { return estimatedTime; }
    }
}