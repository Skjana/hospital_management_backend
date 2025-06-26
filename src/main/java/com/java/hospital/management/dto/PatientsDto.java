package com.java.hospital.management.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Validated
public class PatientsDto {
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
