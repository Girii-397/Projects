package com.healthcare.hospitalintegration;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface HospitalRepository extends JpaRepository<Hospital, UUID> {
    // Custom queries if needed
}