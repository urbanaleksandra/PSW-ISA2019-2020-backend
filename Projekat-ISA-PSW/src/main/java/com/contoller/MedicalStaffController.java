package com.contoller;

import com.model.Doctor;
import com.model.MedicalStaff;
import com.model.Patient;
import com.service.MedicalStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/osobljePromjena", method= RequestMethod.POST)
    public @ResponseBody ResponseEntity<Doctor> changeInfo(@RequestBody Doctor mdNovi){
        Doctor md = (Doctor) medicalStaffService.findByUsername(mdNovi.getUsername());
        if(md != null){
            md.setFirstName(mdNovi.getFirstName());
            md.setLastName(mdNovi.getLastName());
            md.setCity(mdNovi.getCity());
            md.setCountry(mdNovi.getCountry());
            md.setEmail(mdNovi.getEmail());
            md.setMobileNumber(mdNovi.getMobileNumber());
            medicalStaffService.save(md);
        }
        else{
           // Patient patient = new Patient(patientNovi.getUsername(), patientNovi.getPassword(), patientNovi.getFirstName(), patientNovi.getLastName(), patientNovi.getEmail(), patientNovi.getAddress(), patientNovi.getCity(), patientNovi.getCountry(), patientNovi.getMobileNumber(), patientNovi.getJmbg());

           // patientService.save(patient);
        }
        return new ResponseEntity<>(md, HttpStatus.OK);

    }
}
