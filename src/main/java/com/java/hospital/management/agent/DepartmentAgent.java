package com.java.hospital.management.agent;

import com.java.hospital.management.converter.DepartmentConverter;
import com.java.hospital.management.dto.DepartmentDto;
import com.java.hospital.management.dto.ResponseDto;
import com.java.hospital.management.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentAgent {

    private final DepartmentService departmentService;
    private final DepartmentConverter departmentConverter;

    public ResponseDto saveDepartment(DepartmentDto departmentDto) {
        return departmentService.saveDepartment(departmentConverter.convert(departmentDto));
    }

    public List<DepartmentDto> getAllDepartmentList() {
        return departmentService.getAllDepartmentList()
                .stream().map(departmentConverter::convertDto).toList();
    }
}
