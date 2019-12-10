package com.service;

import com.model.HolidayRequest;
import com.repository.HolidayRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HolidayRequestService {

    @Autowired
    private HolidayRequestRepository holidayRequestRepository;

    private HolidayRequest save(HolidayRequest holidayRequest){
        return save(holidayRequest);
    }
}
