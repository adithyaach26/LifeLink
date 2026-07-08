package com.lifelink.lifelink_backend.controller;

import com.lifelink.lifelink_backend.entity.BloodRequest;
import com.lifelink.lifelink_backend.service.BloodRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bloodrequests")
public class BloodRequestController {

    @Autowired
    private BloodRequestService bloodRequestService;

    @PostMapping
    public BloodRequest addBloodRequest(@RequestBody BloodRequest bloodRequest) {
        return bloodRequestService.saveBloodRequest(bloodRequest);
    }

    @GetMapping
    public List<BloodRequest> getAllBloodRequests() {
        return bloodRequestService.getAllBloodRequests();
    }

    @GetMapping("/{id}")
    public BloodRequest getBloodRequest(@PathVariable Long id) {
        return bloodRequestService.getBloodRequestById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteBloodRequest(@PathVariable Long id) {
        bloodRequestService.deleteBloodRequest(id);
        return "Blood Request Deleted Successfully";
    }

    @PutMapping("/approve/{id}")
    public String approveBloodRequest(@PathVariable Long id) {
        return bloodRequestService.approveRequest(id);
    }
}