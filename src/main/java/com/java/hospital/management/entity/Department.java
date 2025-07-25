package com.java.hospital.management.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "department")
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departmentId;
    private String name;             // e.g., "Cardiology", "Pediatrics"
    private String code;             // Optional: e.g., "CARDIO", "PED"
    private String description;      // Optional: e.g., "Heart and blood vessel care"
    private String location;         // Optional: Floor/Room info or building (e.g., "2nd Floor, Block B")
    private String phoneNumber;      // Optional: Direct contact for department
    private String email;            // Optional: contact@cardiology.hospital.com
    private Boolean isActive ;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}



