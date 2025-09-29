package com.healthcare.hospital;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue
    private UUID id;

    private UUID patientId;
    private UUID doctorId;
    private Date dateTime;
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;
    private String notes;
    private Integer queueNumber;
    private Date estimatedArrivalTime;
    private Boolean notificationSent;
    private Boolean checkedIn;

    public enum AppointmentStatus {
        SCHEDULED, RESCHEDULED, CANCELLED, WAITING, IN_QUEUE, ARRIVED
    }

    // Getters and setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getPatientId() { return patientId; }
    public void setPatientId(UUID patientId) { this.patientId = patientId; }
    public UUID getDoctorId() { return doctorId; }
    public void setDoctorId(UUID doctorId) { this.doctorId = doctorId; }
    public Date getDateTime() { return dateTime; }
    public void setDateTime(Date dateTime) { this.dateTime = dateTime; }
    public AppointmentStatus getStatus() { return status; }
    public void setStatus(AppointmentStatus status) { this.status = status; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    public Integer getQueueNumber() { return queueNumber; }
    public void setQueueNumber(Integer queueNumber) { this.queueNumber = queueNumber; }
    public Date getEstimatedArrivalTime() { return estimatedArrivalTime; }
    public void setEstimatedArrivalTime(Date estimatedArrivalTime) { this.estimatedArrivalTime = estimatedArrivalTime; }
    public Boolean getNotificationSent() { return notificationSent; }
    public void setNotificationSent(Boolean notificationSent) { this.notificationSent = notificationSent; }
    public Boolean getCheckedIn() { return checkedIn; }
    public void setCheckedIn(Boolean checkedIn) { this.checkedIn = checkedIn; }
}