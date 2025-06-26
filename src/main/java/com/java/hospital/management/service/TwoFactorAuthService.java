package com.java.hospital.management.service;

import com.java.hospital.management.config.SmsService;
import com.java.hospital.management.config.TwoFactorCodeGenerator;
import com.java.hospital.management.dto.LoginDto;
import com.java.hospital.management.dto.OtpVerificationDto;
import com.java.hospital.management.dto.ResponseDto;
import com.java.hospital.management.entity.Staff;
import com.java.hospital.management.entity.TwoFactorCode;
import com.java.hospital.management.entity.UserLoginDetails;
import com.java.hospital.management.repository.StaffsRepository;
import com.java.hospital.management.repository.TwoFactorCodeRepository;
import com.java.hospital.management.repository.UserLoginDetailsRepository;
import lombok.RequiredArgsConstructor;
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
//    private final SmsService smsService;

    public ResponseDto generateOtp(LoginDto dto) {

        String email = dto.getEmailAddress();
        String password = dto.getPassword();

        Optional<Staff> staff = staffsRepository.findByEmailAddressAndPasswordAndIsDeletedFalse(email, password);
        Optional<UserLoginDetails> lastLogin = userLoginDetailsRepository
                .findTopByUsernameOrderByLoginDateDescLoginTimeDesc(email);

        int invalidCount = 1;
        if (lastLogin.isPresent()) {
            Boolean lastSuccess = lastLogin.get().getIsSuccess();
            if (lastSuccess != null && !lastSuccess) {
                invalidCount = lastLogin.get().getInvalidLoginCount() + 1;
            }
        }

        if (staff.isPresent()) {
            saveLoginAttempt(email, true, 0);
            String otp = TwoFactorCodeGenerator.generateCode();
            TwoFactorCode code = TwoFactorCode.builder()
                    .staffId(staff.get().getStaffId())
                    .code(otp)
                    .generatedAt(LocalDateTime.now())
                    .build();
            twoFactorCodeRepository.save(code);
//            smsService.sendOtpSms(staff.get().getContactNumber(), otp);
            return ResponseDto.builder()
                    .id(String.valueOf(staff.get().getStaffId()))
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

}
