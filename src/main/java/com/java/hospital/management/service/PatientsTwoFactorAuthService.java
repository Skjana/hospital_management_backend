package com.java.hospital.management.service;

import com.java.hospital.management.config.TwoFactorCodeGenerator;
import com.java.hospital.management.dto.LoginDto;
import com.java.hospital.management.dto.OtpVerificationDto;
import com.java.hospital.management.dto.ResponseDto;
import com.java.hospital.management.entity.Patients;
import com.java.hospital.management.entity.TwoFactorCode;
import com.java.hospital.management.entity.UserLoginDetails;
import com.java.hospital.management.repository.PatientsRepository;
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
public class PatientsTwoFactorAuthService {
    private final PatientsRepository patientsRepository;
    private final TwoFactorCodeRepository twoFactorCodeRepository;
    private final UserLoginDetailsRepository userLoginDetailsRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseDto generateOtp(LoginDto loginDto) {

        String email = loginDto.getEmailAddress();
        String rawPassword = loginDto.getPassword();

        Optional<Patients> patientOpt = patientsRepository.findByEmailAddressAndIsDeletedFalse(email);
        if (patientOpt.isEmpty()) {
            throw new IllegalArgumentException("Invalid email or password");
        }
        Patients patient = patientOpt.get();
        if (!passwordEncoder.matches(rawPassword, patient.getPassword())) {
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

        if (patientOpt.isPresent()) {
            saveLoginAttempt(email, true, 0);
            String otp = TwoFactorCodeGenerator.generateCode();
            TwoFactorCode code = TwoFactorCode.builder()
                    .patientId(patientOpt.get().getPatientId())
                    .code(otp)
                    .generatedAt(LocalDateTime.now())
                    .build();
            twoFactorCodeRepository.save(code);
            return ResponseDto.builder()
                    .id(String.valueOf(patientOpt.get().getPatientId()))
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
