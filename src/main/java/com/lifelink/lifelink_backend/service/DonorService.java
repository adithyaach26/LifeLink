package com.lifelink.lifelink_backend.service;


import org.springframework.data.domain.Sort;
import com.lifelink.lifelink_backend.entity.Donor;
import com.lifelink.lifelink_backend.repository.DonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lifelink.lifelink_backend.exception.DonorNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class DonorService {

    @Autowired
    private DonorRepository donorRepository;

    public Donor saveDonor(Donor donor) {
        return donorRepository.save(donor);
    }

    public List<Donor> getAllDonors() {
        return donorRepository.findAll();
    }

    public List<Donor> getDonorsSortedByAgeDesc() {
        return donorRepository.findAll(Sort.by(Sort.Direction.DESC, "age"));
    }

    public List<Donor> getDonorsByBloodGroup(String bloodGroup) {
        return donorRepository.findByBloodGroup(bloodGroup);
    }

    public List<Donor> getDonorsByName(String name) {
        return donorRepository.findByName(name);
    }

    public List<Donor> getDonorsByAge(int age) {
        return donorRepository.findDonorsByAge(age);
    }

    public Donor getDonorById(Long id) {
        return donorRepository.findById(id)
                .orElseThrow(() -> new DonorNotFoundException(
                        "Donor not found with ID: " + id));
    }

    public Donor updateDonor(Long id, Donor updatedDonor) {

        Donor donor = donorRepository.findById(id).orElse(null);

        if (donor != null) {
            donor.setName(updatedDonor.getName());
            donor.setBloodGroup(updatedDonor.getBloodGroup());
            donor.setAge(updatedDonor.getAge());
            donor.setPhone(updatedDonor.getPhone());

            return donorRepository.save(donor);
        }

        return null;
    }

    public void deleteDonor(Long id) {
        donorRepository.deleteById(id);
    }

    public Page<Donor> getDonors(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return donorRepository.findAll(pageable);

    }
}
