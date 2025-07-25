package com.java.hospital.management.converter;

import com.java.hospital.management.dto.UserLoginDetailsDto;
import com.java.hospital.management.entity.UserLoginDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserLoginDetailsConverter {

    public UserLoginDetailsDto convert(UserLoginDetails userLoginDetails) {
        return UserLoginDetailsDto.builder()
                .userLoginDetailsId(userLoginDetails.getUserLoginDetailsId())
                .username(userLoginDetails.getUsername())
                .loginDate(userLoginDetails.getLoginDate())
                .loginTime(userLoginDetails.getLoginTime())
                .invalidLoginCount(userLoginDetails.getInvalidLoginCount())
                .isSuccess(userLoginDetails.getIsSuccess())
                .build();
    }
}
