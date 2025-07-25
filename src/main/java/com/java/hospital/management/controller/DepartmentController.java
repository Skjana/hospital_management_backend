package com.java.hospital.management.controller;

import com.java.hospital.management.agent.DepartmentAgent;
import com.java.hospital.management.dto.DepartmentDto;
import com.java.hospital.management.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentAgent departmentAgent;

    @PostMapping("/save")
    public ResponseDto saveDepartment(@RequestBody DepartmentDto departmentDto){
        return departmentAgent.saveDepartment(departmentDto);
    }

    @GetMapping("/all")
    public List<DepartmentDto> getAllDepartmentList(){
        return departmentAgent.getAllDepartmentList();
    }


}
