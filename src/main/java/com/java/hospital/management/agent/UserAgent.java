package com.java.hospital.management.agent;

import com.java.hospital.management.converter.UserConverter;
import com.java.hospital.management.dto.ResponseDto;
import com.java.hospital.management.dto.UserDto;
import com.java.hospital.management.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAgent {
    private final UserService userService;
    private final UserConverter userConverter;

    public ResponseDto createAccount(UserDto userDto) {
        return userService.createAccount(userConverter.convert(userDto));
    }

}
