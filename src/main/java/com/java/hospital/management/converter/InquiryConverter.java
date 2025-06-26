package com.java.hospital.management.converter;

import com.java.hospital.management.dto.InquiryDto;
import com.java.hospital.management.entity.Inquiry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class InquiryConverter {

    public Inquiry convert(InquiryDto inquiryDto) {
        return Inquiry.builder()
                .id(inquiryDto.getId())
                .fullName(inquiryDto.getFullName())
                .email(inquiryDto.getEmail())
                .phone(inquiryDto.getPhone())
                .inquiryType(inquiryDto.getInquiryType())
                .message(inquiryDto.getMessage())
                .termsAgreement(inquiryDto.getTermsAgreement())
                .termsAccepted(inquiryDto.isTermsAccepted())
                .postDate(LocalDate.now())
                .isDeleted(Boolean.FALSE)
                .build();
    }

    public InquiryDto convert(Inquiry inquiry){
        return  InquiryDto.builder()
                .id(inquiry.getId())
                .fullName(inquiry.getFullName())
                .email(inquiry.getEmail())
                .phone(inquiry.getPhone())
                .inquiryType(inquiry.getInquiryType())
                .message(inquiry.getMessage())
                .termsAgreement(inquiry.getTermsAgreement())
                .termsAccepted(inquiry.isTermsAccepted())
                .postDate(inquiry.getPostDate())
                .isDeleted(inquiry.isDeleted())
                .build();
    }

}
