package com.java.hospital.management.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

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
    @Column(name = "inquiry_id")
    private Long id;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "inquiry_type")
    private String inquiryType;
    @Column(columnDefinition = "TEXT")
    private String message;
    @Column(name = "terms_agreement")
    private String termsAgreement;
    @Column(name = "terms_accepted")
    private boolean termsAccepted;
    @Column(name = "is_deleted")
    private boolean isDeleted;
    @Column(name = "post_date")
    private LocalDate postDate;
}
