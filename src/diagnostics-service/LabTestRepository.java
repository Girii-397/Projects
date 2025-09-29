package com.healthcare.diagnostics;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface LabTestRepository extends JpaRepository<LabTest, UUID> {
    // Add custom queries if needed
}