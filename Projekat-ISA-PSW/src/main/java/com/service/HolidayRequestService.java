package com.service;

import com.model.HolidayRequest;
import com.repository.HolidayRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HolidayRequestService {

    @Autowired
    private HolidayRequestRepository holidayRequestRepository;


    public HolidayRequest findById(Long id) { return  holidayRequestRepository.findById(id).get(); }

    public List<HolidayRequest> findAll(){
        return holidayRequestRepository.findAll();
    }

    public HolidayRequest save(HolidayRequest holidayRequest){
        return holidayRequestRepository.save(holidayRequest);
    }
}
