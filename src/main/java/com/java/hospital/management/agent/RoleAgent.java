package com.java.hospital.management.agent;


import com.java.hospital.management.converter.RoleConverter;
import com.java.hospital.management.dto.ResponseDto;
import com.java.hospital.management.dto.RoleDto;
import com.java.hospital.management.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleAgent {

    private final RoleService roleService;
    private final RoleConverter roleConverter;

    public List<RoleDto> getAllUserRoleList() {
        return roleService.getAllUserRoleList().stream().map(roleConverter::convert).toList();
    }

    public ResponseDto saveUserRole(RoleDto roleDto) {
        return roleService.saveUserRole(roleConverter.convert(roleDto));
    }

}
