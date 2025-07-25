package com.java.hospital.management.entity;

import com.java.hospital.management.config.AppointmentStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class FollowUpAppointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "original_appointment_id")
    private Appointment originalAppointment;

    @ManyToOne(optional = false)
    @JoinColumn(name = "patient_id")
    private Patients patient;

    @ManyToOne(optional = false)
    @JoinColumn(name = "doctor_id")
    private Staff doctor;

    private LocalDateTime followUpDateTime;

    private String purpose;

    @Enumerated(EnumType.ORDINAL)
    private AppointmentStatus status;

    private boolean isDeleted;

}
