package com.contoller;

import com.dto.ClinicAdministratorDTO;
import com.dto.ClinicDTO;
import com.dto.DoctorDTO;
import com.model.*;
import com.repository.ClinicAdministratorRepository;
import com.service.AppointmentService;
import com.service.ClinicAdministratorService;
import com.service.ClinicService;
import com.service.MedicalStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ClinicController {

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private ClinicAdministratorService clinicAdministratorService;

    @Autowired
    MedicalStaffService medicalStaffService;

    @Autowired
    AppointmentService appointmentService;


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
        clinic.setLat(clinicDTO.getLat());
        clinic.setLongitude(clinicDTO.getLongitude());
        System.out.println(clinic.getName());
        clinicService.save(clinic);

        return new ResponseEntity<>(clinic, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/api/get-clinics", method= RequestMethod.GET)
    public ResponseEntity<List<ClinicDTO>> getClinic(){
        List<Clinic> clinics = clinicService.findAll();
        List<ClinicDTO> clinisDTOS = new ArrayList<>();
        for(Clinic c: clinics){
            ClinicDTO CDTO = new ClinicDTO(c.getId(), c.getName(), c.getAddress(),
            c.getPricelist(), c.getDescription(), c.getProfit(), c.getRating(), c.getLongitude(), c.getLat());
            clinisDTOS.add(CDTO);
        }
        return new ResponseEntity<List<ClinicDTO>>(clinisDTOS, HttpStatus.OK);

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/api/get-search-clinics/{date}/{type}", method= RequestMethod.GET)
    public ResponseEntity getSearchClinic(@PathVariable("date") String date, @PathVariable("type") String type){

        try{
            List<Clinic> clinics = clinicService.getSearchClinics(date,type);
            List<ClinicDTO> clinisDTOS = new ArrayList<>();
            for(Clinic c: clinics){
                ClinicDTO CDTO = new ClinicDTO(c.getId(), c.getName(), c.getAddress(),
                        c.getPricelist(), c.getDescription(), c.getProfit(), c.getRating(), c.getLongitude(), c.getLat());
                clinisDTOS.add(CDTO);
            }
            return new ResponseEntity<>(clinisDTOS, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Invalid clinics", HttpStatus.NOT_FOUND);
        }

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/api/get-search-doctors/{date}/{imeKlinike}/{tipPregleda}", method= RequestMethod.GET)
    public ResponseEntity getSearchDoctors(@PathVariable("date") String date, @PathVariable("imeKlinike") String imeKlinike, @PathVariable("tipPregleda") String tipPregleda){

        try{
            List<Doctor> doctors = clinicService.getSearchDoctor(date, imeKlinike, tipPregleda);
            List<DoctorDTO> doctorsDTO = new ArrayList<>();
            for(Doctor doc : doctors){
                DoctorDTO DDTO = new DoctorDTO(doc.getId(), doc.getUsername(), doc.getPassword(), doc.getFirstName(), doc.getLastName());
                DDTO.setRole(doc.getRole());
                DDTO.setAddress(doc.getAddress());
                DDTO.setCity(doc.getCity());
                DDTO.setCountry(doc.getCountry());
                DDTO.setEmail(doc.getEmail());
                DDTO.setJmbg(doc.getJmbg());
                DDTO.setMobileNumber(doc.getMobileNumber());
                DDTO.setPocetakRadnogVremena(doc.getPocetakRadnogVremena());
                DDTO.setKrajRadnogVremena(doc.getKrajRadnogVremena());
                doctorsDTO.add(DDTO);
            }
            return new ResponseEntity<>(doctorsDTO, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Invalid doctors", HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/api/get-clinicAdmins/{id}", method= RequestMethod.GET)
    public List<ClinicAdministrator> getClinicAdmins(@PathVariable long id){
        List<ClinicAdministrator> ret = clinicAdministratorService.findByClinicId(id);
        System.out.println("usao i nasao");
        return ret;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/clinicChangeInfo/{name}", method= RequestMethod.POST)
    public @ResponseBody ResponseEntity<ClinicDTO> changeInfo(@RequestBody ClinicDTO newClinic,@PathVariable String name){
        /*Clinic c = (Clinic) clinicService.findByName(name);
        if(c != null){
            c.setLongitude(newClinic.getLongitude());
            c.setLat(newClinic.getLat());
            c.setAddress(newClinic.getAddress());
            c.setDescription(newClinic.getDescription());
            c.setName(newClinic.getName());
            clinicService.save(c);
        }
        else{
            // Patient patient = new Patient(patientNovi.getUsername(), patientNovi.getPassword(), patientNovi.getFirstName(), patientNovi.getLastName(), patientNovi.getEmail(), patientNovi.getAddress(), patientNovi.getCity(), patientNovi.getCountry(), patientNovi.getMobileNumber(), patientNovi.getJmbg());

            // patientService.save(patient);
        }*/

        //vrsi se transakcija u service
        Clinic c= new Clinic();
        try {
            c = clinicService.changeInfo(newClinic,name);
        }catch (Exception e){
            ClinicDTO clinicDTO=new ClinicDTO(c);
            return new ResponseEntity<>(clinicDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        ClinicDTO clinicDTO=new ClinicDTO(c);
        return new ResponseEntity<>(clinicDTO, HttpStatus.OK);

    }



}
