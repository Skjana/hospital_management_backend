package com.java.hospital.management.controller;

import com.java.hospital.management.dto.*;
import com.java.hospital.management.service.TwoFactorAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/resetPassword/sendOtp")
    public ResponseDto sendResetPasswordOtp(@RequestBody ResetPasswordRequestDto dto) {
        return twoFactorAuthService.generateResetPasswordOtp(dto.getEmailAddress());
    }

    @PostMapping("/resetPassword/confirm")
    public ResponseDto resetPassword(@RequestBody ResetPasswordDto dto) {
        return twoFactorAuthService.resetPasswordWithOtp(dto);
    }

}
