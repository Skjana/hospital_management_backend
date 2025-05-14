package com.java.hospital.management.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class FAQQuestion{
    private Long id;
    private String category;
    private String question;
    private String answer;
    private Boolean isDeleted;
    private LocalDateTime createdDate;
}
