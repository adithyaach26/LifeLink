package com.lifelink.lifelink_backend.controller;

import com.lifelink.lifelink_backend.entity.BloodInventory;
import com.lifelink.lifelink_backend.service.BloodInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class BloodInventoryController {

    @Autowired
    private BloodInventoryService bloodInventoryService;

    // Add blood inventory
    @PostMapping
    public BloodInventory addInventory(@RequestBody BloodInventory inventory) {
        return bloodInventoryService.saveBloodInventory(inventory);
    }

    // Get all inventory
    @GetMapping
    public List<BloodInventory> getAllInventory() {
        return bloodInventoryService.getAllInventory();
    }

    // Get inventory by blood group
    @GetMapping("/{bloodGroup}")
    public BloodInventory getInventory(@PathVariable String bloodGroup) {
        return bloodInventoryService.getInventoryByBloodGroup(bloodGroup);
    }

    // Delete inventory
    @DeleteMapping("/{id}")
    public String deleteInventory(@PathVariable Long id) {
        bloodInventoryService.deleteInventory(id);
        return "Inventory Deleted Successfully";
    }
}