package com.java.hospital.management.controller;

import com.java.hospital.management.agent.RoleAgent;
import com.java.hospital.management.dto.ResponseDto;
import com.java.hospital.management.dto.RoleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userRole")
@RequiredArgsConstructor
public class RoleController {

    private final RoleAgent roleAgent;

    @GetMapping("/all")
    public List<RoleDto> getAllUserRoleList(){
        return roleAgent.getAllUserRoleList();
    }

    @PostMapping("/save")
    public ResponseDto saveUserRole(@RequestBody RoleDto roleDto){
        return roleAgent.saveUserRole(roleDto);
    }

}
