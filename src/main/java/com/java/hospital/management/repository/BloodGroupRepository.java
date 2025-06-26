package com.java.hospital.management.repository;


import com.java.hospital.management.entity.BloodGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BloodGroupRepository extends JpaRepository<BloodGroup,Long> {
    @Query("SELECT b FROM BloodGroup b")
    List<BloodGroup> findAllBloodGroupList();
}
