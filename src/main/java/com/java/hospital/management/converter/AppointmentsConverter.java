package com.java.hospital.management.converter;

import com.java.hospital.management.config.AppointmentStatus;
import com.java.hospital.management.dto.AppointmentDto;
import com.java.hospital.management.entity.Appointment;
import lombok.Builder;
import org.springframework.stereotype.Component;

@Component
@Builder
public class AppointmentsConverter {

    public Appointment convert(AppointmentDto appointmentDto) {
        return Appointment.builder()
                .appointmentDate(appointmentDto.getAppointmentDate())
                .appointmentTime(appointmentDto.getAppointmentTime())
                .departmentName(appointmentDto.getDepartmentName())
                .reason(appointmentDto.getReason())
                .status(AppointmentStatus.CREATED)
                .build();
    }

    public AppointmentDto convertToDto(Appointment appointment) {
        return AppointmentDto.builder()
                .appointmentId(appointment.getAppointmentId())
                .patientId(appointment.getPatient().getPatientId())
                .staffId(appointment.getStaff().getStaffId())
                .appointmentDate(appointment.getAppointmentDate())
                .appointmentTime(appointment.getAppointmentTime())
                .departmentName(appointment.getDepartmentName())
                .status(appointment.getStatus())
                .reason(appointment.getReason())
                .build();
    }

}
