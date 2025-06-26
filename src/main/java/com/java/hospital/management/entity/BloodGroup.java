package com.java.hospital.management.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "blood_group")
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class BloodGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
}
