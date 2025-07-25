package com.java.hospital.management.service;

import com.google.zxing.WriterException;
import com.java.hospital.management.config.AppointmentStatus;
import com.java.hospital.management.config.QRCodeUtil;
import com.java.hospital.management.dto.ResponseDto;
import com.java.hospital.management.entity.Appointment;
import com.java.hospital.management.entity.AppointmentQRCode;
import com.java.hospital.management.entity.Patients;
import com.java.hospital.management.entity.Staff;
import com.java.hospital.management.repository.AppointmentQRCodeRepository;
import com.java.hospital.management.repository.AppointmentRepository;
import com.java.hospital.management.repository.PatientsRepository;
import com.java.hospital.management.repository.StaffsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Base64;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentsService {

    private final PatientsRepository patientsRepository;
    private final StaffsRepository staffsRepository;
    private final AppointmentRepository appointmentRepository;
    private final AppointmentQRCodeRepository appointmentQRCodeRepository;

    @Value("${hospital.name}")
    private String hospitalName;

    public ResponseDto createAppointment(Appointment appointment, Long patientId, Long staffId) {

            Patients patient = patientsRepository.findByPatientIdAndIsDeletedFalse(patientId)
                    .orElseThrow(() -> new IllegalArgumentException("Patient not found"));

            Staff staff = staffsRepository.findByStaffIdAndIsDeletedFalse(staffId)
                    .orElseThrow(() -> new IllegalArgumentException("Doctor not found"));

            appointment.setPatient(patient);
            appointment.setStaff(staff);
            appointment.setStatus(AppointmentStatus.CREATED);
            Appointment saved = appointmentRepository.save(appointment);

            String qrText = "Appointment ID: " + saved.getAppointmentId() +
            "\nPatient: " + patient.getFirstName() + " " + patient.getLastName() +
            "\nDoctor: " + staff.getFirstName() + " " + staff.getLastName() +
            "\nDate: " + saved.getAppointmentDate() +
            "\nTime: " + saved.getAppointmentTime();

        byte[] qrImageBytes = null;
        try {
            qrImageBytes = QRCodeUtil.generateQRCodeImageWithHeader(qrText, 300, 300, hospitalName);
        } catch (WriterException | IOException e) {
            throw new RuntimeException(e);
        }


        AppointmentQRCode qrCode = new AppointmentQRCode();
            qrCode.setAppointment(saved);
            qrCode.setQrCodeImage(qrImageBytes);
            qrCode.setQrText(qrText);
            appointmentQRCodeRepository.save(qrCode);
            return ResponseDto.builder()
                    .id(String.valueOf(saved.getAppointmentId()))
                    .message("Appointment saved successfully")
                    .build();
        }

    public Optional<AppointmentQRCode> findByAppointmentId(Long appointmentId) {
        return appointmentQRCodeRepository.findByAppointment_AppointmentId(appointmentId);
    }

    public ResponseDto getQRCodeBase64(Long appointmentId) {
        AppointmentQRCode qrCode = appointmentQRCodeRepository.findByAppointment_AppointmentId(appointmentId)
                .orElseThrow(() -> new IllegalArgumentException("QR Code not found for this appointment"));
        String base64Image = Base64.getEncoder().encodeToString(qrCode.getQrCodeImage());
        return ResponseDto.builder()
                .id(String.valueOf(appointmentId))
                .message("QR Code fetched successfully")
                .response(base64Image)
                .build();
    }




    public Page<Appointment> getAppointmentsFiltered(Long patientId, String filter, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("appointmentDate").descending().and(Sort.by("appointmentTime").descending()));
        LocalDate today = LocalDate.now();
        LocalTime nowTime = LocalTime.now();
        return switch (filter.toLowerCase()) {
            case "upcoming" -> appointmentRepository.findUpcomingAppointments(patientId, today, nowTime, pageable);
            case "past" -> appointmentRepository.findPastAppointments(patientId, today, nowTime, pageable);
            default -> appointmentRepository.findAllByPatient_PatientId(patientId, pageable);
        };
    }

}

