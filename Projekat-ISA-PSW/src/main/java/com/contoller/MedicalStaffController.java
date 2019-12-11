package com.contoller;

import com.model.MedicalStaff;
import com.service.MedicalStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class MedicalStaffController {

    @Autowired
    MedicalStaffService medicalStaffService;

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/osoblje/{username}", method= RequestMethod.GET)
    public MedicalStaff getMedStaff(@PathVariable String username){

        return medicalStaffService.findByUsername(username);
    }
}
