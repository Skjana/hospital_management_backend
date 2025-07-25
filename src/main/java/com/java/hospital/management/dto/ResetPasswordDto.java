package com.java.hospital.management.dto;

import lombok.Data;

@Data
public class ResetPasswordDto {
    private String otp;
    private String newPassword;
}
