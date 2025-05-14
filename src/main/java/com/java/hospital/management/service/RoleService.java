package com.java.hospital.management.service;

import com.java.hospital.management.constants.ApplicationConstants;
import com.java.hospital.management.dto.ResponseDto;
import com.java.hospital.management.entity.Role;
import com.java.hospital.management.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public List<Role> getAllUserRoleList() {
       return roleRepository.findAllUserRoleList();
    }

    public ResponseDto saveUserRole(Role role) {

    boolean isFaqExist = roleRepository.existsByRoleNameAndIsActiveTrue(role.getRoleName());

        if(isFaqExist){
            throw new IllegalArgumentException(ApplicationConstants.ROLE_ALREADY_EXIST);
        }

        Role savedRole = roleRepository.save(role);
        return ResponseDto.builder()
                .id(String.valueOf(savedRole.getRoleId()))
                .message(ApplicationConstants.ROLE_ADDED_SUCCESSFULLY)
                .build();
    }

}
