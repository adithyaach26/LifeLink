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

    // Add or Update Blood Stock
    @PostMapping
    public BloodInventory addBloodStock(@RequestBody BloodInventory inventory) {
        return bloodInventoryService.addBloodStock(inventory);
    }

    // View All Blood Inventory
    @GetMapping
    public List<BloodInventory> getAllInventory() {
        return bloodInventoryService.getAllInventory();
    }

    // Search Blood Group
    @GetMapping("/{bloodGroup}")
    public BloodInventory getBloodGroup(@PathVariable String bloodGroup) {
        return bloodInventoryService.getByBloodGroup(bloodGroup);
    }
}