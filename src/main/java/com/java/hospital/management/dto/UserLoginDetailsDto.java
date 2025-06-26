package com.java.hospital.management.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDetailsDto {
    private Long userLoginDetailsId;
    private String username;
    private LocalDate loginDate;
    private LocalTime loginTime;
    private Boolean isSuccess;
    private Integer invalidLoginCount;
}
