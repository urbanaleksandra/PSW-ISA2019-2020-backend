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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/cadmin/{username}", method= RequestMethod.GET)
    public ClinicAdministrator getAdmin(@PathVariable String username){
        System.out.println("TU SAM");
        System.out.println(clinicAdministratorService.findByUsername(username).getUsername());
        return clinicAdministratorService.findByUsername(username);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/getMyClinic/{username}", method= RequestMethod.GET)
    public ClinicDTO getClinic(@PathVariable String username){
        System.out.println(clinicAdministratorService.findByUsername(username).getUsername());
        ClinicAdministrator admin=clinicAdministratorService.findByUsername(username);
        Clinic clinic=clinicService.findById(admin.getClinic().getId());
        ClinicDTO clinicDTO=new ClinicDTO(clinic);
        clinicDTO.setLongitude(clinic.getLongitude());
        clinicDTO.setLat(clinic.getLat());
        clinicDTO.setId(clinic.getId());
        return clinicDTO;
     }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/adminChangeInfo", method= RequestMethod.POST)
    public @ResponseBody ResponseEntity<ClinicAdministratorDTO> changeInfo(@RequestBody ClinicAdministratorDTO mdNovi){
        ClinicAdministrator md = (ClinicAdministrator) clinicAdministratorService.findByUsername(mdNovi.getUsername());
        if(md != null){
            md.setPassword(mdNovi.getPassword());
            System.out.println(md.getPassword());
            md.setFirstName(mdNovi.getFirstName());
            md.setLastName(mdNovi.getLastName());
            md.setCity(mdNovi.getCity());
            md.setCountry(mdNovi.getCountry());
            md.setEmail(mdNovi.getEmail());
            md.setMobileNumber(mdNovi.getMobileNumber());
            clinicAdministratorService.save(md);
        }
        else{
            // Patient patient = new Patient(patientNovi.getUsername(), patientNovi.getPassword(), patientNovi.getFirstName(), patientNovi.getLastName(), patientNovi.getEmail(), patientNovi.getAddress(), patientNovi.getCity(), patientNovi.getCountry(), patientNovi.getMobileNumber(), patientNovi.getJmbg());

            // patientService.save(patient);
        }
        ClinicAdministratorDTO d=new ClinicAdministratorDTO(md);
        return new ResponseEntity<>(d, HttpStatus.OK);

    }

}
