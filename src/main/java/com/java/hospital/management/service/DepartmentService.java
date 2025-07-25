package com.java.hospital.management.service;

import com.java.hospital.management.constants.ApplicationConstants;
import com.java.hospital.management.dto.ResponseDto;
import com.java.hospital.management.entity.Department;
import com.java.hospital.management.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    public ResponseDto saveDepartment(Department department) {
        boolean isExistName = departmentRepository.existsByNameIgnoreCaseAndIsActiveTrue(department.getName());
        if (isExistName) {
            throw new IllegalArgumentException(ApplicationConstants.DEPARTMENT_NAME_ALREADY_EXISTS);
        }
        Department savedDepartment = departmentRepository.save(department);
        return ResponseDto.builder()
                .id(String.valueOf(savedDepartment.getDepartmentId()))
                .message(ApplicationConstants.DEPARTMENT_ADDED_SUCCESSFULLY)
                .build();
    }

    public List<Department> getAllDepartmentList() {
        return departmentRepository.getAllDepartmentList();
    }
}
