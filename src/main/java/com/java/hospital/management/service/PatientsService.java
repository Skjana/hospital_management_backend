package com.java.hospital.management.service;

import com.java.hospital.management.constants.ApplicationConstants;
import com.java.hospital.management.converter.PatientsConverter;
import com.java.hospital.management.dto.PatientsDto;
import com.java.hospital.management.dto.ResponseDto;
import com.java.hospital.management.entity.Patients;
import com.java.hospital.management.repository.PatientsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientsService{

    private final PatientsRepository patientsRepository;
    private final PatientsConverter patientsConverter;

    public ResponseDto savepPatient(Patients patients) {
        boolean isExistEmail = patientsRepository.existsByEmailAddressAndIsDeletedFalse(patients.getEmailAddress());
        if(isExistEmail){
            throw new IllegalArgumentException(ApplicationConstants.EMAIL_ALREADY_EXIST);
        }
        if(!isValidEmail(patients.getEmailAddress())){
            throw new IllegalArgumentException(ApplicationConstants.INVALID_EMAIL_FORMAT);
        }
        Patients savedPatient = patientsRepository.save(patients);
        return ResponseDto.builder()
                .id(String.valueOf(savedPatient.getPatientId()))
                .message(ApplicationConstants.USER_ADDED_SUCCESSFULLY)
                .build();
    }

    private boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.com$");
    }

    public List<Patients> getAllPatientsList() {
        return patientsRepository.findAllAllPatientsList();
    }

    public PatientsDto viewPatient(Long patientsId) {
        Patients patient = patientsRepository.findByPatientIdAndIsDeletedFalse(patientsId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        return patientsConverter.convert(patient);
    }


    public PatientsDto updatePatient(Long patientId, PatientsDto patientsDto) {
        Patients existingPatient = patientsRepository.findByPatientIdAndIsDeletedFalse(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        existingPatient.setFirstName(patientsDto.getFirstName());
        existingPatient.setLastName(patientsDto.getLastName());
        existingPatient.setAge(patientsDto.getAge());
        existingPatient.setBloodGroup(patientsDto.getBloodGroup());
        existingPatient.setEmailAddress(patientsDto.getEmailAddress());
        existingPatient.setContactNumber(patientsDto.getContactNumber());
        existingPatient.setPassword(patientsDto.getPassword());
        existingPatient.setRegistrationDate(patientsDto.getRegistrationDate());
        Patients updatedPatient = patientsRepository.save(existingPatient);
        return patientsConverter.convert(updatedPatient);
    }

}
