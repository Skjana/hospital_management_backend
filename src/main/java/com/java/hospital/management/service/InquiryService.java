package com.java.hospital.management.service;

import com.java.hospital.management.constants.ApplicationConstants;
import com.java.hospital.management.dto.ResponseDto;
import com.java.hospital.management.entity.Inquiry;
import com.java.hospital.management.repository.InquiryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class InquiryService {

    private final InquiryRepository inquiryRepository;

    public ResponseDto saveInquiry(Inquiry inquiry) {
        Inquiry savedInquery = inquiryRepository.save(inquiry);
        return ResponseDto.builder()
                .id(String.valueOf(savedInquery.getId()))
                .message(ApplicationConstants.INQUIRY_SENT_SUCCESSFULLY)
                .build();
    }

    public List<Inquiry> getAllInquiryList() {
        return inquiryRepository.findByIsDeletedFalse();
    }
}
