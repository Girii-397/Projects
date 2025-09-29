package com.healthcare.hospital;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/hospital/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    @PreAuthorize("hasRole('PATIENT') or hasRole('HOSPITAL')")
    public Appointment scheduleAppointment(@RequestBody Appointment appointment) {
        return appointmentService.schedule(appointment);
    }

    @PutMapping("/{id}/reschedule")
    @PreAuthorize("hasRole('PATIENT') or hasRole('DOCTOR') or hasRole('HOSPITAL')")
    public Appointment rescheduleAppointment(@PathVariable UUID id, @RequestBody Appointment updatedAppointment) {
        return appointmentService.reschedule(id, updatedAppointment);
    }

    @PutMapping("/{id}/cancel")
    @PreAuthorize("hasRole('PATIENT') or hasRole('DOCTOR') or hasRole('HOSPITAL')")
    public boolean cancelAppointment(@PathVariable UUID id) {
        return appointmentService.cancel(id);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('PATIENT') or hasRole('DOCTOR') or hasRole('HOSPITAL')")
    public Appointment getAppointment(@PathVariable UUID id) {
        return appointmentService.getById(id);
    }

    @GetMapping("/patient/{patientId}")
    @PreAuthorize("hasRole('PATIENT') or hasRole('DOCTOR') or hasRole('HOSPITAL')")
    public List<Appointment> getAppointmentsByPatient(@PathVariable UUID patientId) {
        return appointmentService.getByPatientId(patientId);
    }

    @GetMapping("/doctor/{doctorId}")
    @PreAuthorize("hasRole('DOCTOR') or hasRole('HOSPITAL')")
    public List<Appointment> getAppointmentsByDoctor(@PathVariable UUID doctorId) {
        return appointmentService.getByDoctorId(doctorId);
    }

    @GetMapping
    @PreAuthorize("hasRole('DOCTOR') or hasRole('HOSPITAL')")
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAll();
    }

    @PostMapping("/book-slot")
    @PreAuthorize("hasRole('PATIENT')")
    public Appointment bookSlot(@RequestBody AppointmentService.BookSlotRequest request) {
        return appointmentService.bookSlot(request);
    }

    @GetMapping("/{id}/queue-status")
    @PreAuthorize("hasRole('PATIENT') or hasRole('DOCTOR')")
    public AppointmentService.QueueStatus getQueueStatus(@PathVariable UUID id) {
        return appointmentService.getQueueStatus(id);
    }

    @PutMapping("/{id}/check-in")
    @PreAuthorize("hasRole('PATIENT')")
    public boolean checkIn(@PathVariable UUID id) {
        return appointmentService.checkIn(id);
    }
}