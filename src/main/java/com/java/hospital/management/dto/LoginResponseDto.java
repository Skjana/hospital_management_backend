package com.java.hospital.management.dto;

import lombok.Data;

@Data
public class LoginResponseDto {
    private String userId;
    private String userName;
    private String role;
}
