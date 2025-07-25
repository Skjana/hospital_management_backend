package com.java.hospital.management.controller;

import com.java.hospital.management.agent.StaffAgenet;
import com.java.hospital.management.dto.LoginResponseDto;
import com.java.hospital.management.dto.ResponseDto;
import com.java.hospital.management.dto.StaffDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staffs")
@RequiredArgsConstructor
public class StaffController {

    private final StaffAgenet staffAgenet;

    @PostMapping("/save")
    public ResponseDto saveStaff(@RequestBody StaffDto staffDto){
        return staffAgenet.saveStaff(staffDto);
    }

    @GetMapping("/detail/{staffId}")
    public LoginResponseDto getStaffLoginDetail(@PathVariable long staffId){
        return staffAgenet.getStaffLoginDetail(staffId);
    }

    @GetMapping("/all")
    public List<StaffDto> getAllStaffList(){
        return staffAgenet.getAllStaffList();
    }

    @GetMapping("/all/doctors")
    public List<StaffDto> getAllDoctorsList(){
        return staffAgenet.getAllDoctorsList();
    }

    @GetMapping("/{staffId}")
    public StaffDto viewStaff(@PathVariable Long staffId) {
        return staffAgenet.viewStaff(staffId);
    }
}
