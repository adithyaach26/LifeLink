package com.lifelink.lifelink_backend.service;

import com.lifelink.lifelink_backend.entity.BloodInventory;
import com.lifelink.lifelink_backend.repository.BloodInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BloodInventoryService {

    @Autowired
    private BloodInventoryRepository bloodInventoryRepository;

    // Add or Update Blood Stock
    public BloodInventory addBloodStock(BloodInventory inventory) {

        BloodInventory existingInventory =
                bloodInventoryRepository.findByBloodGroup(inventory.getBloodGroup())
                        .orElse(null);

        if (existingInventory != null) {

            Integer currentUnits = existingInventory.getAvailableUnits();

            if (currentUnits == null) {
                currentUnits = 0;
            }

            existingInventory.setAvailableUnits(
                    currentUnits + inventory.getAvailableUnits()
            );

            return bloodInventoryRepository.save(existingInventory);
        }

        return bloodInventoryRepository.save(inventory);
    }

    // View All Inventory
    public List<BloodInventory> getAllInventory() {
        return bloodInventoryRepository.findAll();
    }

    // Search by Blood Group
    public BloodInventory getByBloodGroup(String bloodGroup) {

        return bloodInventoryRepository.findByBloodGroup(bloodGroup)
                .orElseThrow(() ->
                        new RuntimeException("Blood Group Not Found"));
    }
}