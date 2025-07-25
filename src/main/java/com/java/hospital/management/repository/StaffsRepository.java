package com.java.hospital.management.repository;

import com.java.hospital.management.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StaffsRepository extends JpaRepository<Staff,Long> {

    Optional<Staff> findByStaffIdAndIsDeletedFalse(Long staffId);

    boolean existsByEmailAddressAndIsDeletedFalse(String emailAddress);

    @Query("SELECT s FROM Staff s WHERE s.isDeleted = false")
    List<Staff> findAllStaffList();

    Optional<Staff> findByEmailAddressAndIsDeletedFalse(String emailAddress);

    @Query("SELECT s FROM Staff s WHERE s.isDeleted = false AND s.role.roleId = 2")
    List<Staff> getAllDoctorsList();

    @Query("SELECT s FROM Staff s WHERE s.isDeleted = false AND s.role.roleId = 2 AND s.department.departmentId = :departmentId")
    List<Staff> getAllDoctorsByDepartmentId(Long departmentId);

}
