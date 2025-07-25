package com.java.hospital.management.agent;

import com.java.hospital.management.converter.AppointmentsConverter;
import com.java.hospital.management.dto.AppointmentDto;
import com.java.hospital.management.dto.PaginatedResponse;
import com.java.hospital.management.dto.ResponseDto;
import com.java.hospital.management.entity.Appointment;
import com.java.hospital.management.entity.AppointmentQRCode;
import com.java.hospital.management.service.AppointmentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AppointmentsAgent {

    private final AppointmentsService appointmentsService;
    private final AppointmentsConverter appointmentsConverter;

    public ResponseDto createAppointment(AppointmentDto appointmentDto) {
            Appointment appointment = appointmentsConverter.convert(appointmentDto);
            return appointmentsService.createAppointment(appointment, appointmentDto.getPatientId(), appointmentDto.getStaffId());
    }

    public byte[] getQRCodeImage(Long appointmentId) {
        AppointmentQRCode qrCode = appointmentsService.findByAppointmentId(appointmentId)
                .orElseThrow(() -> new NoSuchElementException("QR Code not found for appointment ID " + appointmentId));

        return qrCode.getQrCodeImage();
    }

    public ResponseDto viewQRCode(Long appointmentId) {
        return appointmentsService.getQRCodeBase64(appointmentId);
    }

    public PaginatedResponse<AppointmentDto> getAppointmentsWithFilter(Long patientId, String filter, int pageNumber, int pageSize) {
        Page<Appointment> page = appointmentsService.getAppointmentsFiltered(patientId, filter, pageNumber, pageSize);
        List<AppointmentDto> dtos = page.getContent().stream()
                .map(appointmentsConverter::convertToDto)
                .toList();
        return new PaginatedResponse<>(
                dtos,
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast()
        );
    }

}
