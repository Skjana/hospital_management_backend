package com.java.hospital.management.repository;

import com.java.hospital.management.entity.AppointmentQRCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppointmentQRCodeRepository extends JpaRepository<AppointmentQRCode,Long> {
    Optional<AppointmentQRCode> findByAppointment_AppointmentId(Long appointmentId);

}
