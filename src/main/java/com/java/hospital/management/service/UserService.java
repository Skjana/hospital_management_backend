package com.java.hospital.management.service;

import com.java.hospital.management.constants.ApplicationConstants;
import com.java.hospital.management.dto.ResponseDto;
import com.java.hospital.management.entity.User;
import com.java.hospital.management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public ResponseDto createAccount(User user) {
        boolean isExistEmail = userRepository.existsByEmailAddressAndIsActiveTrue(user.getEmailAddress());
        if (isExistEmail) {
            throw new IllegalArgumentException(ApplicationConstants.EMAIL_ALREADY_EXIST);
        }
        if (!isValidEmail(user.getEmailAddress())) {
            throw new IllegalArgumentException(ApplicationConstants.INVALID_EMAIL_FORMAT);
        }
        User savedUser = userRepository.save(user);
        return ResponseDto.builder()
                .id(String.valueOf(savedUser.getUserId()))
                .message(ApplicationConstants.USER_ADDED_SUCCESSFULLY)
                .build();
    }

    private boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.com$");
    }
}
