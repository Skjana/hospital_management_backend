package com.java.hospital.management.controller;

import com.java.hospital.management.agent.PatientsAgent;
import com.java.hospital.management.dto.PatientLoginDto;
import com.java.hospital.management.dto.PatientsDto;
import com.java.hospital.management.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientsController {

    private final PatientsAgent patientsAgent;

    @PostMapping("/save")
    public ResponseDto savePatient(@RequestBody PatientsDto patientsDto){
        return patientsAgent.savepPatient(patientsDto);
    }

    @GetMapping("/all")
    public List<PatientsDto>getAllPatientsList(){
        return patientsAgent.getAllPatientsList();
    }

    @GetMapping("/{patientsId}")
    public PatientsDto viewPatient(@PathVariable Long patientsId) {
        return patientsAgent.viewPatient(patientsId);
    }

    @PutMapping("/{patientId}")
    public PatientsDto updatePatient(@PathVariable Long patientId, @RequestBody PatientsDto patientsDto) {
        return patientsAgent.updatePatient(patientId, patientsDto);
    }

    @GetMapping("/detail/{patientId}")
    public PatientLoginDto patientLoginDetail(@PathVariable Long patientId){
        return patientsAgent.patientLoginDetail(patientId);
    }

}
