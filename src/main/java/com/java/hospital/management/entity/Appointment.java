package com.java.hospital.management.entity;

import com.java.hospital.management.config.AppointmentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "patient_id")
    private Patients patient;

    @ManyToOne(optional = false)
    @JoinColumn(name = "staff_id")
    private Staff staff;

    private String departmentName;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;

    private String reason;

    private String updatedBy;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    @Enumerated(EnumType.ORDINAL)
    private AppointmentStatus status;

}
