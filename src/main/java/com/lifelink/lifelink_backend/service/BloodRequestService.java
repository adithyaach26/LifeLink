package com.lifelink.lifelink_backend.service;

import com.lifelink.lifelink_backend.entity.BloodRequest;
import com.lifelink.lifelink_backend.repository.BloodRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lifelink.lifelink_backend.entity.BloodInventory;
import com.lifelink.lifelink_backend.repository.BloodInventoryRepository;

import java.util.List;

@Service
public class BloodRequestService {

    @Autowired
    private BloodRequestRepository bloodRequestRepository;

    public BloodRequest saveBloodRequest(BloodRequest bloodRequest) {
        return bloodRequestRepository.save(bloodRequest);
    }

    public List<BloodRequest> getAllBloodRequests() {
        return bloodRequestRepository.findAll();
    }

    public BloodRequest getBloodRequestById(Long id) {
        return bloodRequestRepository.findById(id).orElse(null);
    }

    public void deleteBloodRequest(Long id) {
        bloodRequestRepository.deleteById(id);
    }

    @Autowired
    private BloodInventoryRepository bloodInventoryRepository;

    public String approveRequest(Long requestId) {

        BloodRequest request = bloodRequestRepository.findById(requestId).orElse(null);

        if (request == null) {
            return "Blood Request Not Found";
        }

        BloodInventory inventory = bloodInventoryRepository
                .findByBloodGroup(request.getBloodGroup())
                .orElse(null);

        if (inventory == null) {
            return "Blood Group Not Available";
        }

        if (inventory.getUnitsAvailable() < request.getUnitsRequired()) {
            return "Insufficient Blood Units";
        }

        inventory.setUnitsAvailable(
                inventory.getUnitsAvailable() - request.getUnitsRequired());

        bloodInventoryRepository.save(inventory);

        request.setStatus("Approved");
        bloodRequestRepository.save(request);

        return "Blood Request Approved Successfully";
    }
}