package com.java.hospital.management.agent;


import com.java.hospital.management.converter.RoleConverter;
import com.java.hospital.management.dto.PaginatedResponse;
import com.java.hospital.management.dto.ResponseDto;
import com.java.hospital.management.dto.RoleDto;
import com.java.hospital.management.entity.Role;
import com.java.hospital.management.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

    public PaginatedResponse<RoleDto> getAllPaginationUserRoleList(int pageNumber, Integer pageSize) {
        Page<Role> page = roleService.getAllPaginationUserRoleList(pageNumber, pageSize);
        List<RoleDto>roleDtos = page.getContent().stream()
                .map(roleConverter::convert)
                .toList();
        return new PaginatedResponse<>(
                roleDtos,
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast()
        );
    }
}
