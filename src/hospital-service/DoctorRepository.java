package com.healthcare.hospital;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface DoctorRepository extends JpaRepository<Doctor, UUID> {
    // Custom queries if needed
}