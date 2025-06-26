package com.java.hospital.management.service;

import com.java.hospital.management.entity.BloodGroup;
import com.java.hospital.management.repository.BloodGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BloodGroupService {
    private final BloodGroupRepository bloodGroupRepository;

    public List<BloodGroup> getAllBloodGroupList() {
        return bloodGroupRepository.findAllBloodGroupList();
    }
}
