package com.java.hospital.management.service;

import com.java.hospital.management.config.TwoFactorCodeGenerator;
import com.java.hospital.management.dto.LoginDto;
import com.java.hospital.management.dto.OtpVerificationDto;
import com.java.hospital.management.dto.ResetPasswordDto;
import com.java.hospital.management.dto.ResponseDto;
import com.java.hospital.management.entity.Staff;
import com.java.hospital.management.entity.TwoFactorCode;
import com.java.hospital.management.entity.UserLoginDetails;
import com.java.hospital.management.repository.StaffsRepository;
import com.java.hospital.management.repository.TwoFactorCodeRepository;
import com.java.hospital.management.repository.UserLoginDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TwoFactorAuthService {

    private final StaffsRepository staffsRepository;
    private final TwoFactorCodeRepository twoFactorCodeRepository;
    private final UserLoginDetailsRepository userLoginDetailsRepository;
    private final PasswordEncoder passwordEncoder;

//    private final SmsService smsService;

    public ResponseDto generateOtp(LoginDto dto) {

        String email = dto.getEmailAddress();
        String rawPassword = dto.getPassword();

        Optional<Staff> staffOpt = staffsRepository.findByEmailAddressAndIsDeletedFalse(email);
        Staff staff = staffOpt.get();

        if (!passwordEncoder.matches(rawPassword, staff.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        Optional<UserLoginDetails> lastLogin = userLoginDetailsRepository
                .findTopByUsernameOrderByLoginDateDescLoginTimeDesc(email);
        int invalidCount = 1;

        if (lastLogin.isPresent()) {
            Boolean lastSuccess = lastLogin.get().getIsSuccess();
            if (lastSuccess != null && !lastSuccess) {
                invalidCount = lastLogin.get().getInvalidLoginCount() + 1;
            }
        }

        if (staffOpt.isPresent()) {
            saveLoginAttempt(email, true, 0);
            String otp = TwoFactorCodeGenerator.generateCode();
            TwoFactorCode code = TwoFactorCode.builder()
                    .staffId(staffOpt.get().getStaffId())
                    .code(otp)
                    .generatedAt(LocalDateTime.now())
                    .build();
            twoFactorCodeRepository.save(code);
//            smsService.sendOtpSms(staff.get().getContactNumber(), otp);
            return ResponseDto.builder()
                    .id(String.valueOf(staffOpt.get().getStaffId()))
                    .message("OTP Sent Successfully.")
                    .build();
        } else {
            saveLoginAttempt(email, false, invalidCount);
            return ResponseDto.builder()
                    .message("Invalid username or password.")
                    .build();
        }
    }

    private void saveLoginAttempt(String username, boolean isSuccess, int invalidCount) {

        Optional<UserLoginDetails> lastLoginOpt = userLoginDetailsRepository
                .findTopByUsernameOrderByLoginDateDescLoginTimeDesc(username);
        if (!isSuccess && lastLoginOpt.isPresent()) {
            UserLoginDetails lastLogin = lastLoginOpt.get();
            if (Boolean.TRUE.equals(!lastLogin.getIsSuccess()) && lastLogin.getLoginDate().equals(LocalDate.now())) {
                lastLogin.setInvalidLoginCount(invalidCount);
                lastLogin.setLoginTime(LocalTime.now());
                userLoginDetailsRepository.save(lastLogin);
                return;
            }
        }
        UserLoginDetails newLogin = UserLoginDetails.builder()
                .username(username)
                .loginDate(LocalDate.now())
                .loginTime(LocalTime.now())
                .isSuccess(isSuccess)
                .invalidLoginCount(invalidCount)
                .build();
        userLoginDetailsRepository.save(newLogin);
    }

    public ResponseDto verifyOtp(OtpVerificationDto dto) {
        TwoFactorCode latestCode = twoFactorCodeRepository.findTopByCodeOrderByGeneratedAtDesc(dto.getOtp())
                .orElseThrow(() -> new IllegalArgumentException("OTP not found"));

        if (!latestCode.getCode().equals(dto.getOtp()) ||
                latestCode.getGeneratedAt().isBefore(LocalDateTime.now().minusMinutes(5))) {
            throw new IllegalArgumentException("expired OTP");
        }
        return ResponseDto.builder()
                .id(String.valueOf(latestCode.getStaffId()))
                .message("OTP verified successfully. Login successful.")
                .build();
    }

    public ResponseDto generateResetPasswordOtp(String emailAddress) {
        Optional<Staff> staffOpt = staffsRepository.findByEmailAddressAndIsDeletedFalse(emailAddress);

        if (staffOpt.isPresent()) {
            Staff staff = staffOpt.get();
            String otp = TwoFactorCodeGenerator.generateCode();
            TwoFactorCode code = TwoFactorCode.builder()
                    .staffId(staff.getStaffId())
                    .code(otp)
                    .generatedAt(LocalDateTime.now())
                    .build();
            twoFactorCodeRepository.save(code);

            return ResponseDto.builder()
                    .id(String.valueOf(staff.getStaffId()))
                    .message("OTP sent to your registered contact number.")
                    .build();
        } else {
            return ResponseDto.builder()
                    .message("No active account found with this email.")
                    .build();
        }
    }

    public ResponseDto resetPasswordWithOtp(ResetPasswordDto dto) {
        String otp = dto.getOtp();
        String newPassword = dto.getNewPassword();

        Optional<TwoFactorCode> latestCodeOpt = twoFactorCodeRepository
                .findTopByCodeOrderByGeneratedAtDesc(otp);
        if (latestCodeOpt.isEmpty()|| !latestCodeOpt.get().getCode().equals(otp)) {
            return ResponseDto.builder()
                    .message("Invalid OTP.")
                    .build();
        }
        TwoFactorCode code = latestCodeOpt.get();
        if (code.getGeneratedAt().plusMinutes(5).isBefore(LocalDateTime.now())) {
            return ResponseDto.builder()
                    .message("OTP has expired.")
                    .build();
        }
        Optional<Staff> staffOpt = staffsRepository.findByStaffIdAndIsDeletedFalse(code.getStaffId());
        if (staffOpt.isEmpty()) {
            return ResponseDto.builder()
                    .message("Staff not found.")
                    .build();
        }
        Staff staff = staffOpt.get();
        staff.setPassword(newPassword);
        staffsRepository.save(staff);
        return ResponseDto.builder()
                .message("Password reset successful.")
                .build();
    }

}
