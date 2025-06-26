package com.java.hospital.management.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "patients")
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Patients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId;
    private String firstName;
    private String lastName;
    private Long age;
    private String bloodGroup;
    private String emailAddress;
    private String contactNumber;
    private String password;
    private LocalDateTime registrationDate;
    private Boolean isDeleted;
}
