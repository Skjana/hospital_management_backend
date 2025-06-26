package com.java.hospital.management.controller;

import com.java.hospital.management.agent.UserAgent;
import com.java.hospital.management.dto.ResponseDto;
import com.java.hospital.management.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserAgent userAgent;

    @PostMapping("/register")
    public ResponseDto createAccount(@RequestBody UserDto userDto){
        return userAgent.createAccount(userDto);
    }


}
