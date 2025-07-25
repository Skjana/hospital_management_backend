package com.java.hospital.management.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

    @ManyToOne(optional = false)
    @JoinColumn(name = "doctor_id")
    private Staff doctor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "patient_id")
    private Patients patient;

    @Column(nullable = false)
    private LocalDateTime issuedAt;

    @Column(columnDefinition = "TEXT")
    private String diagnosis;

    @Column(columnDefinition = "TEXT")
    private String medications;

    @Column(columnDefinition = "TEXT")
    private String instructions;

    @Column(columnDefinition = "TEXT")
    private String notes;

}
