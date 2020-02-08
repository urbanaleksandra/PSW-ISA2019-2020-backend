package com.contoller;

import com.dto.HolidayRequestDTO;
import com.dto.HospitalRoomDTO;
import com.model.Clinic;
import com.model.HolidayRequest;
import com.model.MedicalStaff;
import com.model.RequestAppointment;
import com.repository.HolidayRequestRepository;
import com.service.EmailService;
import com.service.HolidayRequestService;
import com.service.MedicalStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class HolidayRequestController {

    @Autowired
    private HolidayRequestService holidayRequestService;

    @Autowired
    private EmailService emailService;


    @Autowired
    private MedicalStaffService medicalStaffService;

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/getHolidayRequests", method= RequestMethod.GET)
    public List<HolidayRequest> getHolidayReq(){

        return holidayRequestService.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/getHolidayRequests2/{username}", method= RequestMethod.GET)
    public List<HolidayRequestDTO> getHolidayReq2(@PathVariable String username){
        List<HolidayRequest> list=holidayRequestService.findAll();
        List<HolidayRequestDTO> list2=new ArrayList<>();
        for(int i=0;i<list.size();i++){
            HolidayRequest h=list.get(i);
            HolidayRequestDTO dto=new HolidayRequestDTO();
            dto.setDateEnd(h.getDateEnd());
            dto.setDateStart(h.getDateStart());
            dto.setUsername(h.getMedicalStaff().getUsername());
            dto.setId(h.getId());
            dto.setConfirmed(h.isConfirmed());

            if(!h.isFinished()){
                list2.add(dto);
            }

        }
        return list2;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/changeConfirmation/{message}", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<HolidayRequestDTO> change(@RequestBody HolidayRequestDTO holidayreq,@PathVariable String message){

        HolidayRequestDTO holidayRequest = new HolidayRequestDTO();
        try{
            holidayRequest = holidayRequestService.changeConfirmation(holidayreq, message);
            return new ResponseEntity<>(holidayRequest, HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(holidayRequest, HttpStatus.NOT_FOUND);
        }

    }



    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/api/holiday-request", method= RequestMethod.POST)
    @PostMapping(consumes = "application/json")
    public void holidayRequest(@RequestBody HolidayRequestDTO holidayRequestDTO){
        HolidayRequest holidayRequest = new HolidayRequest();
        MedicalStaff ms = medicalStaffService.findByUsername(holidayRequestDTO.getUsername());

        holidayRequest.setDateStart(holidayRequestDTO.getDateStart());
        holidayRequest.setDateEnd(holidayRequestDTO.getDateEnd());
        holidayRequest.setMedicalStaff(ms);
        holidayRequest = holidayRequestService.save(holidayRequest);
        ms.getHolidayRequests().add(holidayRequest);
        ms = medicalStaffService.save(ms);

    }
}
