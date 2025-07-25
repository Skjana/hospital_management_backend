package com.java.hospital.management.converter;

import com.java.hospital.management.dto.DoctorDto;
import com.java.hospital.management.entity.Staff;
import lombok.Builder;
import org.springframework.stereotype.Component;

@Component
@Builder
public class DoctorConverter {

    public DoctorDto convert(Staff staff){
        return DoctorDto.builder()
                .staffId(staff.getStaffId())
                .firstName(staff.getFirstName())
                .lastName(staff.getLastName())
                .department(staff.getDepartment().getName())
                .build();
    }
}
