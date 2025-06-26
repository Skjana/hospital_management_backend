package com.java.hospital.management.agent;

import com.java.hospital.management.converter.InquiryConverter;
import com.java.hospital.management.dto.InquiryDto;
import com.java.hospital.management.dto.ResponseDto;
import com.java.hospital.management.service.InquiryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InquiryAgent {

    private final InquiryService inquiryService;
    private final InquiryConverter inquiryConverter;

    public ResponseDto saveInquiry(InquiryDto inquiryDto) {
        return inquiryService.saveInquiry(inquiryConverter.convert(inquiryDto));
    }

    public List<InquiryDto> getAllInquiryList() {
        return inquiryService.getAllInquiryList().stream().map(inquiryConverter::convert).toList();
    }
}
