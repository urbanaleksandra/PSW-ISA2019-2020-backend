package com.contoller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.model.RequestAppointment;
import com.model.Surgery;
import com.service.MedicalRecordService;
import com.service.SurgeryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class SurgeriesContoller {

    @Autowired
    private MedicalRecordService medicalRecordService;

    @Autowired
    private SurgeryService surgeryService;

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/getSurgeries/{username}", method= RequestMethod.GET)
    public List<Surgery> getSurgeries(@PathVariable String username){

        return surgeryService.findAll();
    }
}
