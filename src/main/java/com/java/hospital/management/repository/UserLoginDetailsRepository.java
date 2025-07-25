package com.java.hospital.management.repository;

import com.java.hospital.management.entity.UserLoginDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserLoginDetailsRepository extends JpaRepository<UserLoginDetails,Long> {
    Optional<UserLoginDetails> findTopByUsernameOrderByLoginDateDescLoginTimeDesc(String emailAddress);

    @Query("SELECT u FROM UserLoginDetails u ORDER BY u.loginDate DESC")
    Page<UserLoginDetails> findAllLoginDetailsList(Pageable pageable);

}
