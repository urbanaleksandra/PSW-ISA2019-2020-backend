package com.service;

import com.model.HolidayRequest;
import com.repository.HolidayRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HolidayRequestService {

    @Autowired
    private HolidayRequestRepository holidayRequestRepository;

    public List<HolidayRequest> findAll(){
        return holidayRequestRepository.findAll();
    }

    public HolidayRequest save(HolidayRequest holidayRequest){
        return holidayRequestRepository.save(holidayRequest);
    }
}
