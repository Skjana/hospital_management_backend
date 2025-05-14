package com.java.hospital.management.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "inquires")
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Inquiry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private String inquiryType;
    private String agree;
    private boolean isDeleted;
    private LocalDateTime postDate;
}
