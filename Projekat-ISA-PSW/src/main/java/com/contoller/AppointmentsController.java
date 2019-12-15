package com.contoller;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.model.*;
import com.model.RequestAppointment;
import com.service.EmailService;
import com.service.MedicalRecordService;
import com.service.PatientService;
import com.service.RequestAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AppointmentsController {

    @Autowired
    private RequestAppointmentService requestAppointmentService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private MedicalRecordService medicalRecordService;

    @Autowired
    private EmailService emailService;

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/api/add-requestApp", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<RequestAppointment> addRequestApp(@RequestBody RequestAppointment appointment){

        RequestAppointment appointment1 = new RequestAppointment(appointment.getPatient(),appointment.getDate(),appointment.getDescription(),appointment.getDuration());
        //requestAppointmentService.save(appointment1);

        Patient pa = patientService.findByUsername(appointment.getPatient());
        System.out.println(pa.getUsername());
        Long paID = pa.getId();

        MedicalRecord mr = medicalRecordService.findByPatientId(paID);
//        mr.addRequestAppointment(appointment1);
//        medicalRecordService.save(mr);
//        System.out.println(mr.getId());
        appointment1.setMedicalRecord(mr);
        requestAppointmentService.save(appointment1);


//        try {
//            emailService.sendNotificaitionAsync3();
//        }catch( Exception e ){
//            System.out.println("nije poslata poruka");
//        }
        return new ResponseEntity<>(appointment1, HttpStatus.OK);

    }
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/getAppointmentRequests", method= RequestMethod.GET)
    public List<RequestAppointment> getAppointmentReq(){

        return requestAppointmentService.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/getAppointments/{username}", method= RequestMethod.GET)
    public List<RequestAppointment> getAppointments(@PathVariable String username){

        return requestAppointmentService.findAll();
    }
}
