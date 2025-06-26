package com.java.hospital.management.agent;

import com.java.hospital.management.converter.PatientsConverter;
import com.java.hospital.management.dto.PatientsDto;
import com.java.hospital.management.dto.ResponseDto;
import com.java.hospital.management.service.PatientsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientsAgent {

    private final PatientsService patientsService;
    private final PatientsConverter patientsConverter;

    public ResponseDto savepPatient(PatientsDto patientsDto) {
        return patientsService.savepPatient(patientsConverter.convert(patientsDto));
    }

    public List<PatientsDto> getAllPatientsList() {
        return patientsService.getAllPatientsList().stream().map(patientsConverter::convert).toList();
    }

    public PatientsDto viewPatient(Long patientsId) {
        return patientsService.viewPatient(patientsId);
    }

    public PatientsDto updatePatient(Long patientId, PatientsDto patientsDto) {
        return patientsService.updatePatient(patientId, patientsDto);
    }

}
