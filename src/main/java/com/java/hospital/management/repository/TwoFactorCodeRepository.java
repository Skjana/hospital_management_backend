package com.java.hospital.management.repository;

import com.java.hospital.management.entity.TwoFactorCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TwoFactorCodeRepository extends JpaRepository<TwoFactorCode,Long> {
    Optional<TwoFactorCode> findTopByCodeOrderByGeneratedAtDesc(String otp);
}
