package com.java.hospital.management.repository;

import com.java.hospital.management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByEmailAddressAndIsActiveTrue(String emailAddress);
}
