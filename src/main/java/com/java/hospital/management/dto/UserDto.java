package com.java.hospital.management.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Validated
public class UserDto {
    private Long userId;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String contactNumber;
    private String password;
    private Long roleId;
    private Boolean isActive;
    private LocalDate registrationDate;
}
