package com.java.hospital.management.service;

import com.java.hospital.management.entity.Country;
import com.java.hospital.management.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final CountryRepository countryRepository;
    public List<Country> getAllCountryList() {
        return countryRepository.findAllCountryList();
    }
}
