package com.java.hospital.management.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "user_login_details")
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class UserLoginDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_login_details_id", nullable = false)
    private Long userLoginDetailsId;
    @Column(name = "username", length = 250)
    private String username;
    @Column(name = "login_date")
    private LocalDate loginDate;
    @Column(name = "login_time")
    private LocalTime loginTime;
    @Column(name = "is_success")
    private Boolean isSuccess;
    @Column(name = "invalid_login_count")
    private Integer invalidLoginCount;
}
