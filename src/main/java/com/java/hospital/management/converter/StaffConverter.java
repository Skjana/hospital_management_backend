package com.java.hospital.management.converter;

import com.java.hospital.management.dto.StaffDto;
import com.java.hospital.management.entity.Department;
import com.java.hospital.management.entity.Role;
import com.java.hospital.management.entity.Staff;
import com.java.hospital.management.repository.DepartmentRepository;
import com.java.hospital.management.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class StaffConverter {

    private final DepartmentRepository departmentRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public Staff convert(StaffDto staffDto) {
        Department department = departmentRepository.findById(staffDto.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found with ID: " + staffDto.getDepartmentId()));
        Role role = roleRepository.findById(staffDto.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found with ID: " + staffDto.getRoleId()));
        return Staff.builder()
                .staffId(staffDto.getStaffId())
                .firstName(staffDto.getFirstName())
                .lastName(staffDto.getLastName())
                .bloodGroup(staffDto.getBloodGroup())
                .emailAddress(staffDto.getEmailAddress())
                .contactNumber(staffDto.getContactNumber())
                .department(department)
                .password(passwordEncoder.encode(staffDto.getPassword()))
                .role(role)
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
