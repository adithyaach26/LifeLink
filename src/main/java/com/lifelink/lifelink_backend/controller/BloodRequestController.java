package com.lifelink.lifelink_backend.controller;

import com.lifelink.lifelink_backend.entity.BloodRequest;
import com.lifelink.lifelink_backend.service.BloodRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requests")
public class BloodRequestController {

    @Autowired
    private BloodRequestService bloodRequestService;

    // Create Blood Request
    @PostMapping
    public BloodRequest createRequest(@RequestBody BloodRequest request) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        return bloodRequestService.createRequest(request, email);
    }

    // Get All Requests
    @GetMapping
    public List<BloodRequest> getAllRequests() {
        return bloodRequestService.getAllRequests();
    }

    // Get My Requests
    @GetMapping("/my")
    public List<BloodRequest> getMyRequests() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        return bloodRequestService.getMyRequests(email);
    }

    // Get Request By ID
    @GetMapping("/{id}")
    public BloodRequest getRequestById(@PathVariable Long id) {
        return bloodRequestService.getRequestById(id);
    }

    // Delete Request
    @DeleteMapping("/{id}")
    public String deleteRequest(@PathVariable Long id) {
        return bloodRequestService.deleteRequest(id);
    }
}