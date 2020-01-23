package com.contoller;

import com.dto.MedicalStaffDTO;
import com.model.Doctor;
import com.model.MedicalStaff;
import com.service.DoctorService;
import com.service.MedicalStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class MedicalStaffController {

    @Autowired
    MedicalStaffService medicalStaffService;

    @Autowired
    DoctorService doctorService;

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/osoblje/{username}", method= RequestMethod.GET)
    public MedicalStaff getMedStaff(@PathVariable String username){

        return medicalStaffService.findByUsername(username);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/doctors", method= RequestMethod.GET)
    public List<MedicalStaff> getDoctors(){

        return medicalStaffService.findByRole("doctor");
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/add-doctor", method= RequestMethod.POST)
    public void addDoc(@RequestBody MedicalStaffDTO staff){
        Doctor d=new Doctor();
        d.setFirstName(staff.getFirstName());
        d.setAddress(staff.getAddress());
        d.setMobileNumber(staff.getMobileNumber());
        d.setKrajRadnogVremena(staff.getKrajRadnogVremena());
        d.setLastName(staff.getLastName());
        d.setEmail(staff.getEmail());
        d.setCountry(staff.getCountry());
        d.setCity(staff.getCity());
        d.setPassword(staff.getPassword());
        d.setJmbg(staff.getJmbg());
        d.setRole("doctor");
        d.setUsername(staff.getUsername());
        medicalStaffService.save(d);

    }


    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/osobljePromjena", method= RequestMethod.POST)
    public @ResponseBody ResponseEntity<Doctor> changeInfo(@RequestBody Doctor mdNovi){
        Doctor md = (Doctor) medicalStaffService.findByUsername(mdNovi.getUsername());
        if(md != null){
            md.setPassword(mdNovi.getPassword());
            System.out.println(md.getPassword());
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
