package com.contoller;

import com.model.ClinicAdministrator;
import com.model.Patient;
import com.model.RequestAppointment;
import com.model.RequestUser;
import com.service.EmailService;
import com.service.RequestAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AppointmentsController {

    @Autowired
    private RequestAppointmentService requestAppointmentService;
    @Autowired
    private EmailService emailService;

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/api/add-requestApp", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<RequestAppointment> addRequestApp(@RequestBody RequestAppointment appointment){

        RequestAppointment appointment1 = new RequestAppointment(appointment.getDate(),appointment.getDescription(),appointment.getDuration());
        requestAppointmentService.save(appointment1);

        try {
            emailService.sendNotificaitionAsync3();
        }catch( Exception e ){
            System.out.println("nije poslata poruka");
        }
        return new ResponseEntity<>(appointment1, HttpStatus.OK);

    }
}
