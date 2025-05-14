package com.java.hospital.management.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "countries")
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "country_name")
    private String name;
    @Column(name = "phone_code")
    private String phoneCode;
    @Column(name = "country_code")
    private String countryCode;
    @Column(name = "iso_code")
    private String isoCode;
    @Column(name = "status")
    private Boolean status;
}
