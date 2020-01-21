package com.contoller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.model.*;
import com.service.ClinicAdministratorService;
import com.service.MedicalRecordService;
import com.service.SurgeryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.SeparatorUI;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class SurgeriesContoller {

    @Autowired
    private MedicalRecordService medicalRecordService;

    @Autowired
    private SurgeryService surgeryService;

    @Autowired
    private ClinicAdministratorService clinicAdministratorService;


    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/getSurgeries/{username}", method= RequestMethod.GET)
    public List<Surgery> getSurgeries(@PathVariable String username){

        return surgeryService.findAll();
    }


    // cadmina prosledjujem kako bih znala u kojoj je on klinici,
    // i onda uzimam samo one operacije koje su vezane za tu kliniku
    @CrossOrigin(origins = "http//localhost:4200")
    @RequestMapping(value = "/api/surgeries-res-rooms/{cadmin}", method = RequestMethod.GET)
    public List<Surgery> getSurgeriesInClinic(@PathVariable String cadmin){
        System.out.println("usao");
        List<Surgery> surgeries = new ArrayList<>();
        List<Surgery> ret = new ArrayList<Surgery>();
        ClinicAdministrator ca;
        try{
            ca = clinicAdministratorService.findByUsername(cadmin);
        } catch (Exception e){
            System.out.println("nije ulogovan cadmin");
            return null;
        }

        surgeries = surgeryService.findByClinicId(ca.getClinic().getId());
        for (Surgery s:surgeries) {
            if(s.getHospitalRoom() == null)
                ret.add(s);


        }

        return ret;
    }
}
