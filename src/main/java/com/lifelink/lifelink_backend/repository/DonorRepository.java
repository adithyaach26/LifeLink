package com.lifelink.lifelink_backend.repository;

import com.lifelink.lifelink_backend.entity.Donor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DonorRepository extends JpaRepository<Donor, Long> {

    List<Donor> findByBloodGroup(String bloodGroup);

    List<Donor> findByName(String name);

    @Query("SELECT d FROM Donor d WHERE d.age = :age")
    List<Donor> findDonorsByAge(@Param("age") int age);

}