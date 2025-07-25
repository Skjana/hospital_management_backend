package com.java.hospital.management.controller;


import com.java.hospital.management.agent.UserLoginDetailsControllerAgent;
import com.java.hospital.management.dto.PaginatedResponse;
import com.java.hospital.management.dto.UserLoginDetailsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userDetails")
@RequiredArgsConstructor
public class UserLoginDetailsController {

    private final UserLoginDetailsControllerAgent userLoginDetailsControllerAgent;

    @GetMapping("/all")
    public PaginatedResponse<UserLoginDetailsDto> getAllLoginDetailsList(
        @RequestParam Integer pageNumber,
        @RequestParam Integer pageSize) {
        int adjustedPageNumber = Math.max(0, pageNumber - 1);
    return userLoginDetailsControllerAgent.getAllLoginDetailsList(adjustedPageNumber, pageSize);
    }

}
