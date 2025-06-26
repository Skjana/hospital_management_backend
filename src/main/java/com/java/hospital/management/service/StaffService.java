package com.java.hospital.management.service;

import com.java.hospital.management.constants.ApplicationConstants;
import com.java.hospital.management.converter.StaffConverter;
import com.java.hospital.management.dto.ResponseDto;
import com.java.hospital.management.dto.StaffDto;
import com.java.hospital.management.entity.Patients;
import com.java.hospital.management.entity.Staff;
import com.java.hospital.management.repository.StaffsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StaffService {

    private final StaffsRepository staffsRepository;
    private final StaffConverter staffConverter;

    public ResponseDto saveStaff(Staff staff) {
        boolean isExistEmail = staffsRepository.existsByEmailAddressAndIsDeletedFalse(staff.getEmailAddress());
        if(isExistEmail){
            throw new IllegalArgumentException(ApplicationConstants.EMAIL_ALREADY_EXIST);
        }
        if(!isValidEmail(staff.getEmailAddress())){
            throw new IllegalArgumentException(ApplicationConstants.INVALID_EMAIL_FORMAT);
        }
        Staff savedStaff = staffsRepository.save(staff);
        return ResponseDto.builder()
                .id(String.valueOf(savedStaff.getStaffId()))
                .message(ApplicationConstants.USER_ADDED_SUCCESSFULLY)
                .build();
    }

    private boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.com$");
    }

    public List<Staff> getAllStaffList() {
        return staffsRepository.findAllStaffList();
    }

    public StaffDto viewStaff(Long staffId) {
        Staff staff = staffsRepository.findByStaffIdAndIsDeletedFalse(staffId)
                .orElseThrow(() -> new RuntimeException("Staff not found with id: " + staffId));
        return staffConverter.convert(staff);
    }

}
