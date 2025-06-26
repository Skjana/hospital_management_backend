package com.java.hospital.management.converter;

import com.java.hospital.management.dto.PatientsDto;
import com.java.hospital.management.entity.Patients;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class PatientsConverter {

    public Patients convert(PatientsDto patientsDto) {
        return Patients.builder()
                .patientId(patientsDto.getPatientId())
                .firstName(patientsDto.getFirstName())
                .lastName(patientsDto.getLastName())
                .age(patientsDto.getAge())
                .bloodGroup(patientsDto.getBloodGroup())
                .emailAddress(patientsDto.getEmailAddress())
                .contactNumber(patientsDto.getContactNumber())
                .password(patientsDto.getPassword())
                .registrationDate(LocalDateTime.now())
                .isDeleted(Boolean.FALSE)
                .build();
    }

    public PatientsDto convert(Patients patients){
        return PatientsDto.builder()
                .patientId(patients.getPatientId())
                .firstName(patients.getFirstName())
                .lastName(patients.getLastName())
                .age(patients.getAge())
                .bloodGroup(patients.getBloodGroup())
                .emailAddress(patients.getEmailAddress())
                .contactNumber(patients.getContactNumber())
                .password(patients.getPassword())
                .registrationDate(patients.getRegistrationDate())
                .isDeleted(patients.getIsDeleted())
                .build();
    }

}
