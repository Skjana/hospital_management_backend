package com.java.hospital.management.repository;

import com.java.hospital.management.entity.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {

    @Query("""
    SELECT a FROM Appointment a
    WHERE a.patient.patientId = :patientId
      AND (a.appointmentDate > :today OR (a.appointmentDate = :today AND a.appointmentTime > :nowTime))""")
    Page<Appointment> findUpcomingAppointments(@Param("patientId") Long patientId,
                                               @Param("today") LocalDate today,
                                               @Param("nowTime") LocalTime nowTime,
                                               Pageable pageable);

    @Query("""
    SELECT a FROM Appointment a
    WHERE a.patient.patientId = :patientId
      AND (a.appointmentDate < :today OR (a.appointmentDate = :today AND a.appointmentTime < :nowTime))""")
    Page<Appointment> findPastAppointments(@Param("patientId") Long patientId,
                                           @Param("today") LocalDate today,
                                           @Param("nowTime") LocalTime nowTime,
                                           Pageable pageable);
    Page<Appointment> findAllByPatient_PatientId(Long patientId, Pageable pageable);

}
