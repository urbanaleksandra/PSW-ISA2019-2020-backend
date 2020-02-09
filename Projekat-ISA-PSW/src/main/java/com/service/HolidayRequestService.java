package com.service;

import com.dto.HolidayRequestDTO;
import com.model.HolidayRequest;
import com.repository.HolidayRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class HolidayRequestService implements HolidayRequestServiceInterface {

    @Autowired
    private HolidayRequestRepository holidayRequestRepository;

    @Autowired
    private EmailService emailService;


    public HolidayRequest findById(Long id) { return  holidayRequestRepository.findById(id).get(); }

    public List<HolidayRequest> findAll(){
        return holidayRequestRepository.findAll();
    }

    public HolidayRequest save(HolidayRequest holidayRequest){
        return holidayRequestRepository.save(holidayRequest);
    }

    //transakcija
    @Transactional(rollbackFor = {RuntimeException.class},readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED)
    public HolidayRequestDTO changeConfirmation(HolidayRequestDTO holidayreq, String message){

        HolidayRequest holidayRequest = holidayRequestRepository.findById(holidayreq.getId()).get();
        HolidayRequestDTO holidayRequestDTO = new HolidayRequestDTO();

        System.out.println(holidayreq.getConfirmed());
        holidayRequest.setConfirmed(holidayreq.getConfirmed());
        holidayRequest.setFinished(true);
        System.out.println(holidayRequest);
        holidayRequestRepository.save(holidayRequest);

        try {
            if(holidayRequest.isConfirmed()){
                emailService.sendNotificaitionAsync5(); }
            else {
                emailService.sendNotificaitionAsync6(message);
            }
        }catch( Exception e ){
            System.out.println("nije poslata poruka");
        }

        return holidayRequestDTO;

    }
}
