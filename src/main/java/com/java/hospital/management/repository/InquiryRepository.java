package com.java.hospital.management.repository;

import com.java.hospital.management.entity.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InquiryRepository extends JpaRepository<Inquiry,Long> {
    List<Inquiry> findByIsDeletedFalse();
}
