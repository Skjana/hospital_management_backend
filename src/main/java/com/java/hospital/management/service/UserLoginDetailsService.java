package com.java.hospital.management.service;

import com.java.hospital.management.entity.UserLoginDetails;
import com.java.hospital.management.repository.UserLoginDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserLoginDetailsService {

    private final UserLoginDetailsRepository userLoginDetailsRepository;

    public Page<UserLoginDetails> getAllLoginDetailsList(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "loginTime"));
        return userLoginDetailsRepository.findAllLoginDetailsList(pageable);
    }

}
