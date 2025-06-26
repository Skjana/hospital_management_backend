package com.java.hospital.management.agent;

import com.java.hospital.management.converter.BloodGroupConverter;
import com.java.hospital.management.dto.BloodGroupDto;
import com.java.hospital.management.service.BloodGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BloodGroupAgent {

    private final BloodGroupService bloodGroupService;
    private final BloodGroupConverter bloodGroupConverter;

    public List<BloodGroupDto> getAllBloodGroupList() {
        return bloodGroupService.getAllBloodGroupList().stream().map(bloodGroupConverter::convert).toList();
    }
}
