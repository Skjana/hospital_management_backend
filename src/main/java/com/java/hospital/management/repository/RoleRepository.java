package com.java.hospital.management.repository;

import com.java.hospital.management.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role,Long> {

    @Query("SELECT r FROM Role r WHERE r.isActive = true")
    List<Role> findAllUserRoleList();

    boolean existsByRoleNameAndIsActiveTrue(String roleName);

    @Query("SELECT r FROM Role r WHERE r.isActive = true")
    Page<Role> getAllPaginationUserRoleList(Pageable pageable);
}
