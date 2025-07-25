package com.java.hospital.management.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class AppointmentQRCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qrcodeId;

    @OneToOne
    private Appointment appointment;

    @Lob
    private byte[] qrCodeImage;
    private String qrText;
}
