package com.java.hospital.management.converter;

import com.java.hospital.management.dto.CountryDto;
import com.java.hospital.management.entity.Country;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CountryConverter {
    public CountryDto convert(Country country) {
        return CountryDto.builder()
                .id(country.getId())
                .name(country.getName())
                .countryCode(country.getCountryCode())
                .phoneCode(country.getPhoneCode())
                .isoCode(country.getIsoCode())
                .status(country.getStatus())
                .build();
    }
}
