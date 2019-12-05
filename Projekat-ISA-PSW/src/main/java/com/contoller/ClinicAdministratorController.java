package com.contoller;

import com.dto.ClinicAdministratorDTO;
import com.dto.ClinicDTO;
import com.model.Clinic;
import com.model.ClinicAdministrator;
import com.repository.ClinicAdministratorRepository;
import com.repository.ClinicRepository;
import com.service.ClinicAdministratorService;
import com.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ClinicAdministratorController {

    @Autowired
    private ClinicAdministratorService clinicAdministratorService;

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private ClinicRepository clinicRepository;

    @Autowired
    private ClinicAdministratorRepository clinicAdministratorRepository;

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/api/add-admin/{id}", method= RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public void addAdministrator(@RequestBody ClinicAdministrator clinicAdministrator, @PathVariable long id){
        Clinic clinic = clinicService.findById(id);
        clinicAdministrator.setClinic(clinic);
        //clinic.getClinicAdministrator().add(clinicAdministrator);
        System.out.println(clinic);
        //clinicAdministrator.setClinic();
        System.out.println(clinicAdministrator);
        clinicAdministratorRepository.save(clinicAdministrator);

    }

}
