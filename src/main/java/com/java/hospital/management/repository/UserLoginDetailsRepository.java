package com.java.hospital.management.repository;

import com.java.hospital.management.entity.UserLoginDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserLoginDetailsRepository extends JpaRepository<UserLoginDetails,Long> {
    Optional<UserLoginDetails> findTopByUsernameOrderByLoginDateDescLoginTimeDesc(String emailAddress);
}
