package com.java.hospital.management.controller;

import com.java.hospital.management.agent.StaffAgenet;
import com.java.hospital.management.dto.ResponseDto;
import com.java.hospital.management.dto.StaffDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staffs")
@RequiredArgsConstructor
//@CrossOrigin(origins = "http://localhost:4200")
public class StaffController {

    private final StaffAgenet staffAgenet;

    @PostMapping("/save")
    public ResponseDto saveStaff(@RequestBody StaffDto staffDto){
        return staffAgenet.saveStaff(staffDto);
    }

    @GetMapping("/all")
    public List<StaffDto> getAllStaffList(){
        return staffAgenet.getAllStaffList();
    }

    @GetMapping("/{staffId}")
    public StaffDto viewStaff(@PathVariable Long staffId) {
        return staffAgenet.viewStaff(staffId);
    }
}
