package com.java.hospital.management.agent;

import com.java.hospital.management.converter.DoctorConverter;
import com.java.hospital.management.dto.DoctorDto;
import com.java.hospital.management.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorAgent {
    private final DoctorService doctorService;
    private final DoctorConverter doctorConverter;

    public List<DoctorDto> getAllDoctorsList() {
        return doctorService.getAllDoctorsList().stream().map(doctorConverter::convert).toList();
    }

    public List<DoctorDto> getAllDoctorsByDepartmentId(Long departmentId) {
        return doctorService.getAllDoctorsByDepartmentId(departmentId)
                .stream()
                .map(doctorConverter::convert)
                .toList();
    }
}
