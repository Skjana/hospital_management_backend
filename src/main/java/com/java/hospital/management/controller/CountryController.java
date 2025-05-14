package com.java.hospital.management.controller;

import com.java.hospital.management.agent.CountryAgent;
import com.java.hospital.management.dto.CountryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/country")
@RequiredArgsConstructor
public class CountryController {

    private final CountryAgent countryAgent;

    @GetMapping("/all")
    public List<CountryDto> getAllCountryList(){
        return countryAgent.getAllCountryList();
    }

}
