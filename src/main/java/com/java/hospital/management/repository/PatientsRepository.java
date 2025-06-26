package com.java.hospital.management.repository;

import com.java.hospital.management.entity.Patients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PatientsRepository extends JpaRepository<Patients,Long> {

    boolean existsByEmailAddressAndIsDeletedFalse(String emailAddress);

    @Query("SELECT p FROM Patients p WHERE p.isDeleted = false")
    List<Patients> findAllAllPatientsList();

    Optional<Patients> findByPatientIdAndIsDeletedFalse(Long patientId);

    Optional<Patients> findByEmailAddressAndPasswordAndIsDeletedFalse(String email, String password);
}
