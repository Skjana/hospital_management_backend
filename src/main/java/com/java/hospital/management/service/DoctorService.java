package com.java.hospital.management.service;

import com.java.hospital.management.entity.Staff;
import com.java.hospital.management.repository.StaffsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final StaffsRepository staffsRepository;

    public List<Staff> getAllDoctorsList() {
        return staffsRepository.getAllDoctorsList();
    }

    public List<Staff> getAllDoctorsByDepartmentId(Long departmentId) {
        return staffsRepository.getAllDoctorsByDepartmentId(departmentId);
    }
}
