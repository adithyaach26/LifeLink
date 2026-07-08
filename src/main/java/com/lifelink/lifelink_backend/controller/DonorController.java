package com.lifelink.lifelink_backend.controller;

import com.lifelink.lifelink_backend.entity.Donor;
import com.lifelink.lifelink_backend.service.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RestController
@RequestMapping("/api/donors")
public class DonorController {

    @Autowired
    private DonorService donorService;

    @PostMapping
    public Donor addDonor(@RequestBody Donor donor) {
        return donorService.saveDonor(donor);
    }

    @GetMapping
    public List<Donor> getAllDonors() {
        return donorService.getAllDonors();
    }

    @GetMapping("/page")
    public Page<Donor> getDonors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int size) {

        return donorService.getDonors(page, size);
    }

    @GetMapping("/sort/age/desc")
    public List<Donor> getDonorsSortedByAgeDesc() {
        return donorService.getDonorsSortedByAgeDesc();
    }

    @GetMapping("/bloodgroup/{bloodGroup}")
    public List<Donor> getDonorsByBloodGroup(@PathVariable String bloodGroup) {
        return donorService.getDonorsByBloodGroup(bloodGroup);
    }

    @GetMapping("/name/{name}")
    public List<Donor> getDonorsByName(@PathVariable String name) {
        return donorService.getDonorsByName(name);
    }

    @GetMapping("/age/{age}")
    public List<Donor> getDonorsByAge(@PathVariable int age) {
        return donorService.getDonorsByAge(age);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Donor> getDonor(@PathVariable Long id) {

        Donor donor = donorService.getDonorById(id);

        if (donor != null) {
            return ResponseEntity.ok(donor);
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public Donor updateDonor(@PathVariable Long id,
                             @RequestBody Donor donor) {

        return donorService.updateDonor(id, donor);
    }

    @DeleteMapping("/{id}")
    public String deleteDonor(@PathVariable Long id) {
        donorService.deleteDonor(id);
        return "Donor Deleted Successfully";
    }

}