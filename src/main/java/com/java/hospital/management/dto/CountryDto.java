package com.java.hospital.management.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CountryDto {
    private Long id;
    private String name;
    private String phoneCode;
    private String countryCode;
    private String isoCode;
    private Boolean status;
}
