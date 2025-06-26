package com.java.hospital.management.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InquiryDto {
    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private String inquiryType;
    private String message;
    private String termsAgreement;
    @JsonProperty("isTermsAccepted")
    private boolean termsAccepted;
    private boolean isDeleted;
    private LocalDate postDate;
}
