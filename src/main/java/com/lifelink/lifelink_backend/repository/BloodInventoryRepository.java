package com.lifelink.lifelink_backend.repository;

import com.lifelink.lifelink_backend.entity.BloodInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BloodInventoryRepository extends JpaRepository<BloodInventory, Long> {

    Optional<BloodInventory> findByBloodGroup(String bloodGroup);

}