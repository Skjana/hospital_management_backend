package com.java.hospital.management.controller;

import com.java.hospital.management.agent.InquiryAgent;
import com.java.hospital.management.dto.InquiryDto;
import com.java.hospital.management.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inquiry")
@RequiredArgsConstructor
public class InquiryController {

    private final InquiryAgent inquiryAgent;

    @PostMapping("/save")
    public ResponseDto saveInquiry(@RequestBody InquiryDto inquiryDto){
        return inquiryAgent.saveInquiry(inquiryDto);
    }

    @GetMapping("/all")
    public List<InquiryDto>getAllInquiryList(){
        return inquiryAgent.getAllInquiryList();
    }


}
