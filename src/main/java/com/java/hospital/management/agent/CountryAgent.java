package com.java.hospital.management.agent;

import com.java.hospital.management.converter.CountryConverter;
import com.java.hospital.management.dto.CountryDto;
import com.java.hospital.management.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryAgent {

    private final CountryService countryService;
    private final CountryConverter countryConverter;

    public List<CountryDto> getAllCountryList() {
        return countryService.getAllCountryList().stream().map(countryConverter::convert).toList();
    }
}
