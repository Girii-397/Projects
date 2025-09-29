package com.healthcare.hospitalintegration;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface StoreRepository extends JpaRepository<Store, UUID> {
    List<Store> findByHospitalId(UUID hospitalId);
}