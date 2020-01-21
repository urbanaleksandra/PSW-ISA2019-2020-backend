package com.contoller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.model.AppointmentType;
import com.service.AppointmentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AppointmentTypeController {

    @Autowired
    private AppointmentTypeService appointmentTypeService;

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/add-appType", method= RequestMethod.POST)
    public void addApp(@RequestBody AppointmentType appointmentType){
        System.out.println((appointmentType));
        appointmentTypeService.save(appointmentType);

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/getAppointmentTypes", method= RequestMethod.GET)
    public List<AppointmentType> getTypes(){

        return appointmentTypeService.findAll();
    }

}
