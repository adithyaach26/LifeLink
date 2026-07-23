package com.lifelink.lifelink_backend.service;

import com.lifelink.lifelink_backend.entity.BloodRequest;
import com.lifelink.lifelink_backend.repository.BloodRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BloodRequestService {

    @Autowired
    private BloodRequestRepository bloodRequestRepository;

    // Create a new blood request
    public BloodRequest createRequest(BloodRequest request, String email) {

        request.setRequestedBy(email);
        request.setStatus("PENDING");

        return bloodRequestRepository.save(request);
    }

    // Get all blood requests
    public List<BloodRequest> getAllRequests() {
        return bloodRequestRepository.findAll();
    }

    // Get requests created by logged-in user
    public List<BloodRequest> getMyRequests(String email) {
        return bloodRequestRepository.findByRequestedBy(email);
    }

    // Get request by ID
    public BloodRequest getRequestById(Long id) {
        return bloodRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Blood Request Not Found"));
    }

    // Delete request
    public String deleteRequest(Long id) {

        bloodRequestRepository.deleteById(id);

        return "Blood Request Deleted Successfully";
    }
}