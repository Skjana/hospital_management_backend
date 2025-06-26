package com.java.hospital.management.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "staffs")
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long staffId;
    private String firstName;
    private String lastName;
    private String bloodGroup;
    private String emailAddress;
    private String contactNumber;
    private Long roleId;
    private String password;
    private LocalDateTime appointmentDate;
    private Boolean isDeleted;
}
