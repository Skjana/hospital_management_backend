package com.java.hospital.management.agent;

import com.java.hospital.management.converter.StaffConverter;
import com.java.hospital.management.dto.ResponseDto;
import com.java.hospital.management.dto.StaffDto;
import com.java.hospital.management.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StaffAgenet {

    private final StaffService staffService;
    private final StaffConverter staffConverter;

    public ResponseDto saveStaff(StaffDto staffDto) {
        return staffService.saveStaff(staffConverter.convert(staffDto));
    }

    public List<StaffDto> getAllStaffList() {
        return staffService.getAllStaffList().stream().map(staffConverter::convert).toList();
    }

    public StaffDto viewStaff(Long staffId) {
        return staffService.viewStaff(staffId);
    }
}
