package com.contoller;

import com.dto.HolidayRequestDTO;
import com.model.Clinic;
import com.model.HolidayRequest;
import com.model.MedicalStaff;
import com.repository.HolidayRequestRepository;
import com.service.HolidayRequestService;
import com.service.MedicalStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class HolidayRequestController {

    @Autowired
    private HolidayRequestService holidayRequestService;

    @Autowired
    private MedicalStaffService medicalStaffService;

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
