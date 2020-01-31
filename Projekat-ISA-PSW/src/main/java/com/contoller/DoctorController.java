package com.contoller;

import com.model.Appointment;
import com.model.Doctor;
import com.service.AppointmentService;
import com.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private AppointmentService appointmentService;

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/getDoctorsForRate/{username}", method= RequestMethod.GET)
    public List<Doctor> getDoctorsForRate(@PathVariable String username){

        List<Appointment> pregledi = appointmentService.findAll();
        List<Doctor> doctors = new ArrayList<>();
        for(Appointment app : pregledi){
            if(app.getPatient().equals(username) && app.isFinished()){
                doctors.add(app.getDoctor());
                //System.out.println(app.getDoctor().getFirstName());
            }
        }

        return doctors;
    }
}
