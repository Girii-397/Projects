package com.healthcare.hospital;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.UUID;
import java.util.Date;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {
    List<Appointment> findByPatientId(UUID patientId);
    List<Appointment> findByDoctorId(UUID doctorId);

    @Query("SELECT MAX(a.queueNumber) FROM Appointment a WHERE a.doctorId = ?1 AND DATE(a.dateTime) = DATE(?2)")
    Integer findMaxQueueByDoctorAndDate(UUID doctorId, Date date);

    @Query("SELECT MIN(a.queueNumber) FROM Appointment a WHERE a.doctorId = ?1 AND DATE(a.dateTime) = DATE(?2) AND a.status = 'IN_QUEUE'")
    Integer findMinQueueByDoctorAndDate(UUID doctorId, Date date);
}