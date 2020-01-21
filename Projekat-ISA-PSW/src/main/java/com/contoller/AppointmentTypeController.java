package com.contoller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.model.AppointmentType;
import com.service.AppointmentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/delete-type", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public  @ResponseBody
    ResponseEntity<AppointmentType> deleteType(@RequestBody AppointmentType type){

        System.out.println(type.getName());
        AppointmentType hr=appointmentTypeService.findByName(type.getName());
        appointmentTypeService.delete(hr);
        AppointmentType hRoom = new AppointmentType(type.getName());


        return new ResponseEntity<>(hRoom, HttpStatus.OK);
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/changeTypeInfo/{name}", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<AppointmentType> changeInfo(@RequestBody AppointmentType type, @PathVariable String name){

        AppointmentType type1 = appointmentTypeService.findByName(name);
        if(type1 != null){
            type1.setName(type.getName());
            appointmentTypeService.save(type1);
        }
        else{
            AppointmentType type2=new AppointmentType();
            type2.setName(type.getName());
            appointmentTypeService.save(type2);
        }
        AppointmentType type3 = new AppointmentType();
        return new ResponseEntity<>(type3, HttpStatus.OK);

    }

}
