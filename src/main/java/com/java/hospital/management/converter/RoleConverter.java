package com.java.hospital.management.converter;


import com.java.hospital.management.dto.RoleDto;
import com.java.hospital.management.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleConverter {

    public RoleDto convert(Role role) {
        return RoleDto.builder()
                .roleId(role.getRoleId())
                .roleName(role.getRoleName())
                .isActive(role.getIsActive())
                .build();
    }

    public Role convert(RoleDto roleDto) {
        return Role.builder()
                .roleId(roleDto.getRoleId())
                .roleName(roleDto.getRoleName())
                .isActive(Boolean.TRUE)
                .build();
    }

}
