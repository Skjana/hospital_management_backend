package com.java.hospital.management.agent;

import com.java.hospital.management.converter.UserLoginDetailsConverter;
import com.java.hospital.management.dto.PaginatedResponse;
import com.java.hospital.management.dto.UserLoginDetailsDto;
import com.java.hospital.management.entity.UserLoginDetails;
import com.java.hospital.management.service.UserLoginDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserLoginDetailsControllerAgent {

    private final UserLoginDetailsService userLoginDetailsService;
    private final UserLoginDetailsConverter userLoginDetailsConverter;

    public PaginatedResponse<UserLoginDetailsDto> getAllLoginDetailsList(int pageNumber, int pageSize) {
        Page<UserLoginDetails> page = userLoginDetailsService.getAllLoginDetailsList(pageNumber, pageSize);
        List<UserLoginDetailsDto> dtos = page.getContent().stream()
                .map(userLoginDetailsConverter::convert)
                .toList();

        return new PaginatedResponse<>(
                dtos,
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast()
        );
    }


}
