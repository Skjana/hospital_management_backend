package com.java.hospital.management.converter;

import com.java.hospital.management.dto.UserDto;
import com.java.hospital.management.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class UserConverter {
    public User convert(UserDto userDto) {
        return User.builder()
                .userId(userDto.getUserId())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .emailAddress(userDto.getEmailAddress())
                .contactNumber(userDto.getContactNumber())
                .password(userDto.getPassword())
                .roleId(userDto.getRoleId())
                .isActive(Boolean.TRUE)
                .registrationDate(LocalDate.now())
                .build();
    }
}
