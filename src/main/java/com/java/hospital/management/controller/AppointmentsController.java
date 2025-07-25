package com.java.hospital.management.controller;

import com.java.hospital.management.agent.AppointmentsAgent;
import com.java.hospital.management.dto.AppointmentDto;
import com.java.hospital.management.dto.PaginatedResponse;
import com.java.hospital.management.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointment")
@RequiredArgsConstructor
public class AppointmentsController {

    private final AppointmentsAgent appointmentsAgentAgent;

    @PostMapping("/save")
    public ResponseDto createAppointment(@RequestBody AppointmentDto appointmentDto){
        return appointmentsAgentAgent.createAppointment(appointmentDto);
    }

    @GetMapping("/myAppointments/{patientId}")
    public PaginatedResponse<AppointmentDto> getAppointmentsWithFilter(
            @PathVariable Long patientId,
            @RequestParam(defaultValue = "all") String filter,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        return appointmentsAgentAgent.getAppointmentsWithFilter(patientId, filter, pageNumber, pageSize);
    }

    @GetMapping("/qrcode/{appointmentId}")
    public ResponseEntity<byte[]> downloadQRCode(@PathVariable Long appointmentId) {
        byte[] imageData = appointmentsAgentAgent.getQRCodeImage(appointmentId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.setContentDisposition(ContentDisposition
                .attachment()
                .filename("appointment_qr_" + appointmentId + ".png")
                .build());
        return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
    }

    @GetMapping("/viewQr/{appointmentId}")
    public ResponseDto viewQRCode(@PathVariable Long appointmentId){
        return appointmentsAgentAgent.viewQRCode(appointmentId);
    }

}

