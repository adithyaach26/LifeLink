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

    // Add blood inventory
    public BloodInventory saveBloodInventory(BloodInventory inventory) {
        return bloodInventoryRepository.save(inventory);
    }

    // Get all inventory
    public List<BloodInventory> getAllInventory() {
        return bloodInventoryRepository.findAll();
    }

    // Find inventory by blood group
    public BloodInventory getInventoryByBloodGroup(String bloodGroup) {
        return bloodInventoryRepository.findByBloodGroup(bloodGroup)
                .orElse(null);
    }

    // Delete inventory
    public void deleteInventory(Long id) {
        bloodInventoryRepository.deleteById(id);
    }
}