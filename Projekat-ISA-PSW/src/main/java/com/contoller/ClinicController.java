package com.contoller;

import com.dto.ClinicDTO;
import com.model.Clinic;
import com.model.ClinicAdministrator;
import com.model.MedicalStaff;
import com.repository.ClinicAdministratorRepository;
import com.service.ClinicAdministratorService;
import com.service.ClinicService;
import com.service.MedicalStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ClinicController {

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private ClinicAdministratorService clinicAdministratorService;

    @Autowired
    MedicalStaffService medicalStaffService;

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/api/add-clinic", method= RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Clinic> addClinic(@RequestBody ClinicDTO clinicDTO){

        Clinic clinic = new Clinic();
        clinic.setName(clinicDTO.getName());
        clinic.setDescription(clinicDTO.getDescription());
        clinic.setAddress(clinicDTO.getAddress());
        clinic.setPricelist(clinicDTO.getPricelist());
        clinic.setProfit(clinicDTO.getProfit());
        System.out.println(clinic.getName());
        clinicService.save(clinic);

        return new ResponseEntity<>(clinic, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/api/get-clinics", method= RequestMethod.GET)
    public List<Clinic> getClinic(){
        List<Clinic> ret = clinicService.findAll();
        return ret;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/api/get-search-clinics/{date}", method= RequestMethod.GET)
    public List<Clinic> getSearchClinic(@PathVariable String date){
        //System.out.println("usao565656565"+date);

//        List<MedicalStaff> doctors = medicalStaffService.findByRole("doctor");
//        List<Long> doctorsId = null;
//        for (MedicalStaff doctor:doctors) {
//            doctorsId.add(doctor.getId());
//        }

        System.out.println(medicalStaffService.findByRole("doctor").get(0).getId());

        List<Clinic> ret = clinicService.findAll();
        return ret;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/api/get-clinicAdmins/{id}", method= RequestMethod.GET)
    public List<ClinicAdministrator> getClinicAdmins(@PathVariable long id){
        List<ClinicAdministrator> ret = clinicAdministratorService.findByClinicId(id);
        System.out.println("usao i nasao");
        return ret;
    }
}
