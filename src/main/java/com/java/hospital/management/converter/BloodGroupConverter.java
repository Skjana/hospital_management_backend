package com.java.hospital.management.converter;

import com.java.hospital.management.dto.BloodGroupDto;
import com.java.hospital.management.entity.BloodGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BloodGroupConverter {

    public BloodGroupDto convert(BloodGroup bloodGroup) {
        return BloodGroupDto.builder()
                .id(bloodGroup.getId())
                .type(bloodGroup.getType())
                .build();
    }

}
