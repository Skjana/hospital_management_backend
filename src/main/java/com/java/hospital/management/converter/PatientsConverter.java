package com.java.hospital.management.converter;

import com.java.hospital.management.dto.PatientLoginDto;
import com.java.hospital.management.dto.PatientsDto;
import com.java.hospital.management.entity.Patients;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class PatientsConverter {
    private final BCryptPasswordEncoder passwordEncoder;
    public Patients convert(PatientsDto patientsDto) {
        return Patients.builder()
                .patientId(patientsDto.getPatientId())
                .firstName(patientsDto.getFirstName())
                .lastName(patientsDto.getLastName())
                .age(patientsDto.getAge())
                .gender(patientsDto.getGender())
                .bloodGroup(patientsDto.getBloodGroup())
                .emailAddress(patientsDto.getEmailAddress())
                .contactNumber(patientsDto.getContactNumber())
                .address(patientsDto.getAddress())
                .password(passwordEncoder.encode(patientsDto.getPassword()))
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


    public PatientLoginDto convertDto(Patients patient) {
        return PatientLoginDto.builder()
                .patientId(patient.getPatientId())
                .fullName(patient.getFirstName() + " " + patient.getLastName())
                .emailAddress(patient.getEmailAddress())
                .build();
    }
}
