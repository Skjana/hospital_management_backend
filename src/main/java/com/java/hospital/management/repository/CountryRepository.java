package com.java.hospital.management.repository;

import com.java.hospital.management.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country,Long> {
    @Query("SELECT c FROM Country c WHERE c.status = true")
    List<Country> findAllCountryList();
}
