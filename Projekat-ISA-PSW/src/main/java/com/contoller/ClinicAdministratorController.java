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
    public void addAdministrator(@RequestBody ClinicAdministratorDTO clinicAdministratorDTO, @PathVariable long id){
        System.out.println("usuao");
        Clinic clinic = clinicService.findById(id);
        ClinicAdministrator ca = new ClinicAdministrator();
        ca.setUsername(clinicAdministratorDTO.getUsername());
        ca.setPassword(clinicAdministratorDTO.getPassword());
        ca.setEmail(clinicAdministratorDTO.getEmail());
        ca.setAddress(clinicAdministratorDTO.getAddress());
        ca.setCity(clinicAdministratorDTO.getCity());
        ca.setCountry(clinicAdministratorDTO.getCountry());
        ca.setJmbg(clinicAdministratorDTO.getJmbg());
        ca.setFirstName(clinicAdministratorDTO.getFirstName());
        ca.setLastName(clinicAdministratorDTO.getLastName());
        ca.setMobileNumber(clinicAdministratorDTO.getMobileNumber());
        ca = clinicAdministratorRepository.save(ca);
        clinic.getClinicAdministrator().add(ca);
        System.out.println(clinic);
        ca.setClinic(clinic);
        System.out.println(ca.getClinic().getId());
        clinicAdministratorRepository.save(ca);
        clinicRepository.save(clinic);
    }

}
