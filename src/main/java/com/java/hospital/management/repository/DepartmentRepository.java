package com.java.hospital.management.repository;

import com.java.hospital.management.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department,Long> {
    boolean existsByNameIgnoreCaseAndIsActiveTrue(String name);

    @Query("SELECT d FROM Department d WHERE d.isActive = true")
    List<Department> getAllDepartmentList();

}
