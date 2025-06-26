package com.java.hospital.management.controller;

import com.java.hospital.management.dto.LoginDto;
import com.java.hospital.management.dto.OtpVerificationDto;
import com.java.hospital.management.dto.ResponseDto;
import com.java.hospital.management.service.TwoFactorAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/2fa")
@RequiredArgsConstructor
public class TwoFactorController {

    private final TwoFactorAuthService twoFactorAuthService;

    @PostMapping("/send")
    public ResponseDto send2FACode(@RequestBody LoginDto loginDto) {
        return twoFactorAuthService.generateOtp(loginDto);
    }

    @PostMapping("/verify")
    public ResponseDto verifyOtp(@RequestBody OtpVerificationDto dto) {
        return twoFactorAuthService.verifyOtp(dto);
    }





}
