package com.java.hospital.management.controller;

import com.java.hospital.management.agent.BloodGroupAgent;
import com.java.hospital.management.dto.BloodGroupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bloodGroups")
@RequiredArgsConstructor
public class BloodGroupController {

    private final BloodGroupAgent bloodGroupAgent;

    @GetMapping("/all")
    public List<BloodGroupDto> getAllBloodGroupList(){
        return bloodGroupAgent.getAllBloodGroupList();
    }
}
