package com.java.hospital.management.controller;

import com.java.hospital.management.agent.DoctorAgent;
import com.java.hospital.management.dto.DoctorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/doctor")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorAgent doctorAgent;

    @GetMapping("/all")
    public List<DoctorDto> getAllDoctorsList(){
        return doctorAgent.getAllDoctorsList();
    }

    @GetMapping("/all/{departmentId}")
    public List<DoctorDto> getAllDoctorsByDepartment(@PathVariable Long departmentId){
        return doctorAgent.getAllDoctorsByDepartmentId(departmentId);
    }

}
