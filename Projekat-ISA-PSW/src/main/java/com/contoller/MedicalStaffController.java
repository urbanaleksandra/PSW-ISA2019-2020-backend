package com.contoller;

import com.dto.MedicalStaffDTO;
import com.model.Clinic;
import com.model.ClinicAdministrator;
import com.model.Doctor;
import com.model.MedicalStaff;
import com.service.ClinicAdministratorService;
import com.service.ClinicService;
import com.service.DoctorService;
import com.service.MedicalStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class MedicalStaffController {

    @Autowired
    MedicalStaffService medicalStaffService;

    @Autowired
    DoctorService doctorService;

    @Autowired
    ClinicService clinicService;


    @Autowired
    ClinicAdministratorService administratorService;

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/osoblje/{username}", method= RequestMethod.GET)
    public MedicalStaff getMedStaff(@PathVariable String username){

        return medicalStaffService.findByUsername(username);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/doctors", method= RequestMethod.GET)
    public List<MedicalStaff> getDoctors(){

        return medicalStaffService.findByRole("doctor");
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/doctors/{usernameAdmin}", method= RequestMethod.GET)
    public List<Doctor> getDoctors(@PathVariable String usernameAdmin){

        ClinicAdministrator admin= administratorService.findByUsername(usernameAdmin);
        List<Doctor> doctors=new ArrayList<>();
        List <MedicalStaff> medicalStaffs=medicalStaffService.findByRole("doctor");
        for(int i=0;i<medicalStaffs.size();i++) {

            Doctor doc=((Doctor) medicalStaffs.get(i));
            if(doc.getClinic().getId().equals(admin.getClinic().getId())){
                doctors.add(doc);
            }
        }
        return doctors;
    }



    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/delete-doc", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public  @ResponseBody
    ResponseEntity<MedicalStaffDTO> deleteDoc(@RequestBody MedicalStaffDTO doctor){

        System.out.println(doctor.getUsername());
        Doctor hr=(Doctor)medicalStaffService.findByUsername(doctor.getUsername());
        Clinic clinic=clinicService.findById(hr.getClinic().getId());
        System.out.println(clinic.getId());
        clinic.getDoctors().remove(hr);
        System.out.println(clinic.getDoctors());
        clinicService.save(clinic);
        medicalStaffService.delete(hr);
        MedicalStaffDTO medStaff = new MedicalStaffDTO();


        return new ResponseEntity<>(medStaff, HttpStatus.OK);
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/add-doctor/{username}", method= RequestMethod.POST)
    public void addDoc(@RequestBody MedicalStaffDTO staff,@PathVariable String username){
        Doctor d=new Doctor();
        d.setFirstName(staff.getFirstName());
        d.setAddress(staff.getAddress());
        d.setMobileNumber(staff.getMobileNumber());
        d.setKrajRadnogVremena(staff.getKrajRadnogVremena());
        d.setLastName(staff.getLastName());
        d.setEmail(staff.getEmail());
        d.setCountry(staff.getCountry());
        d.setCity(staff.getCity());
        d.setPassword(staff.getPassword());
        d.setJmbg(staff.getJmbg());
        d.setRole("doctor");
        d.setUsername(staff.getUsername());
        d.setPocetakRadnogVremena(staff.getPocetakRadnogVremena());
        medicalStaffService.save(d);

        ClinicAdministrator clinicAdministrator=administratorService.findByUsername(username);
        Clinic clinic = clinicService.findById(clinicAdministrator.getClinic().getId());
        if (clinic!=null) {
            clinic.getDoctors().add(d);
            System.out.println(clinic);
            d.setClinic(clinic);
            System.out.println(d.getClinic().getId());
            medicalStaffService.save(d);
            clinicService.save(clinic);

        }
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/osobljePromjena", method= RequestMethod.POST)
    public @ResponseBody ResponseEntity<MedicalStaff> changeInfo(@RequestBody MedicalStaffDTO mdNovi){
        MedicalStaff md = medicalStaffService.findByUsername(mdNovi.getUsername());
        if(md != null){
            md.setPassword(mdNovi.getPassword());
            System.out.println(md.getPassword());
            md.setFirstName(mdNovi.getFirstName());
            md.setLastName(mdNovi.getLastName());
            md.setCity(mdNovi.getCity());
            md.setCountry(mdNovi.getCountry());
            md.setEmail(mdNovi.getEmail());
            md.setMobileNumber(mdNovi.getMobileNumber());
            medicalStaffService.save(md);
        }
        else{
           // Patient patient = new Patient(patientNovi.getUsername(), patientNovi.getPassword(), patientNovi.getFirstName(), patientNovi.getLastName(), patientNovi.getEmail(), patientNovi.getAddress(), patientNovi.getCity(), patientNovi.getCountry(), patientNovi.getMobileNumber(), patientNovi.getJmbg());

           // patientService.save(patient);
        }
        return new ResponseEntity<>(md, HttpStatus.OK);

    }
}
