package com.contoller;


import com.dto.AppointmentDTO;
import com.dto.CalendarEventsDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.model.*;
import com.model.RequestAppointment;
import com.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AppointmentsController {

    @Autowired
    private RequestAppointmentService requestAppointmentService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private MedicalRecordService medicalRecordService;

    @Autowired
    private EmailService emailService;



    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/api/add-requestApp", method=RequestMethod.POST,  produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<RequestAppointment> addRequestApp(@RequestBody AppointmentDTO appointment){

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


        try {
            emailService.sendNotificaitionAsync3();
        }catch( Exception e ){
            System.out.println("nije poslata poruka");
        }

        return new ResponseEntity<>(appointment1, HttpStatus.OK);

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/api/add-requestApp-from-patient", method=RequestMethod.POST,  produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<RequestAppointment> addRequestAppFromPatient(@RequestBody AppointmentDTO appointment){

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
        appointment1.setDoctorUsername(appointment.getDoctorUsername());
        appointment1.setType(appointment.getType());
        requestAppointmentService.save(appointment1);


        try {
            emailService.sendNotificaitionAsync4();
        }catch( Exception e ){
            System.out.println("nije poslata poruka");
        }

        return new ResponseEntity<>(appointment1, HttpStatus.OK);

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/getAppointmentRequests", method= RequestMethod.GET)
    public List<RequestAppointment> getAppointmentReq(){

        return requestAppointmentService.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/getAppointments/{username}", method= RequestMethod.GET)
    public List<Appointment> getAppointments(@PathVariable String username){

        return appointmentService.findAll();
    }

    //preuzimanje svih appointmenta za kalendar
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/api/getAllAppointments", method= RequestMethod.GET)
    public List<CalendarEventsDTO> getAllAppointments() throws ParseException {
        List<Appointment> lista = appointmentService.findAll();
        List<CalendarEventsDTO> eventsDTOS = new ArrayList<CalendarEventsDTO>();

        for (Appointment app: lista) {
            Patient patient = patientService.findByUsername(app.getPatient());
            String title = app.getDescription() + "\n" + patient.getFirstName() + " " + patient.getLastName();
            String color = "";
            if(app.getType().equals("appointment"))
                color = "purple";
            else
                color = "green";

            //dodavanje duration
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Date date = dateFormat.parse(app.getDate());
            long millis = date.getTime();
            millis += app.getDuration() * 60 * 60 * 1000;
            String endDate = dateFormat.format(millis);

            CalendarEventsDTO eventsDTO = new CalendarEventsDTO(title, app.getDate(), endDate, app.getId(), color);
            eventsDTOS.add(eventsDTO);
        }
        return eventsDTOS;
    }
}
