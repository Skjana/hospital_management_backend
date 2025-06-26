package com.java.hospital.management.repository;

import com.java.hospital.management.entity.FAQQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaqRepository extends JpaRepository<FAQQuestion,Long> {
}
