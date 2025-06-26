package com.java.hospital.management.converter;

import com.java.hospital.management.dto.StaffDto;
import com.java.hospital.management.entity.Staff;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class StaffConverter {

    public Staff convert(StaffDto staffDto) {
        return Staff.builder()
                .staffId(staffDto.getStaffId())
                .firstName(staffDto.getFirstName())
                .lastName(staffDto.getLastName())
                .bloodGroup(staffDto.getBloodGroup())
                .emailAddress(staffDto.getEmailAddress())
                .contactNumber(staffDto.getContactNumber())
                .password(staffDto.getPassword())
                .roleId(staffDto.getRoleId())
                .appointmentDate(LocalDateTime.now())
                .isDeleted(Boolean.FALSE)
                .build();
    }

    public StaffDto convert(Staff staff){
        return StaffDto.builder()
                .staffId(staff.getStaffId())
                .firstName(staff.getFirstName())
                .lastName(staff.getLastName())
                .bloodGroup(staff.getBloodGroup())
                .emailAddress(staff.getEmailAddress())
                .contactNumber(staff.getContactNumber())
                .password(staff.getPassword())
                .appointmentDate(staff.getAppointmentDate())
                .isDeleted(staff.getIsDeleted())
                .build();
    }

}
