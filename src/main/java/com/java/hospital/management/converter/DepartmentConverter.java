package com.java.hospital.management.converter;

import com.java.hospital.management.dto.DepartmentDto;
import com.java.hospital.management.entity.Department;
import lombok.Builder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Builder
public class DepartmentConverter {
    public Department convert(DepartmentDto departmentDto) {
        return Department.builder()
                .departmentId(departmentDto.getDepartmentId())
                .name(departmentDto.getName())
                .code(departmentDto.getCode())
                .description(departmentDto.getDescription())
                .location(departmentDto.getLocation())
                .phoneNumber(departmentDto.getPhoneNumber())
                .email(departmentDto.getEmail())
                .isActive(Boolean.TRUE)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public DepartmentDto convertDto(Department department) {
        return DepartmentDto.builder()
                .departmentId(department.getDepartmentId())
                .name(department.getName())
                .description(department.getDescription())
                .build();
    }
}
