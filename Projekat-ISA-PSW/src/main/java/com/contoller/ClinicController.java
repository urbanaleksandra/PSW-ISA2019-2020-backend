package com.contoller;

import com.dto.ClinicAdministratorDTO;
import com.dto.ClinicDTO;
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
    public List<Clinic> getSearchClinic(@PathVariable("date") String date, @PathVariable("type") String type){
//        System.out.println("usao565656565"+date);

        List<MedicalStaff> doctors = medicalStaffService.findByRole("doctor");
        List<Long> doctorsId = new ArrayList<>();
        //System.out.println(doctors);
        for (MedicalStaff doctor:doctors) {
            //System.out.println(doctor.getId());
            doctorsId.add(doctor.getId()); //id svih doktora
        }

        //System.out.println(doctorsId);
//        System.out.println(appointmentService.findByFinished(false));
        List<Long> doctoriKojiSuZauzeti = new ArrayList<>(); //zauzeti doktori za taj datum(njihov id)
        for(Appointment app:appointmentService.findByFinished(false)){
            //System.out.println(app.getDate().split("T")[0]);
            //System.out.println(date);
            if(app.getDate().split("T")[0].equals(date)){
//                System.out.println(app.getDoctor().getId());
                if(!doctoriKojiSuZauzeti.contains(app.getDoctor().getId())){
                    doctoriKojiSuZauzeti.add(app.getDoctor().getId());
                }
            }
        }

        //System.out.println(doctoriKojiSuZauzeti);

        if(doctoriKojiSuZauzeti.size() != 0){
            for (Long id:doctoriKojiSuZauzeti) {
                doctorsId.remove(id); //obrisem id doktora koji su zauzeti, ostanu samo slobodni u doctorsId
            }
        }
        //System.out.println(doctorsId);

        List<Clinic> clinics = new ArrayList<>();
        for(MedicalStaff doctor : doctors){
            for(Long id: doctorsId){
                //System.out.println(((Doctor)doctor).getAppointmentType().getName());
                //System.out.println(type);
                if(!type.equals("-1")){
                    if(doctor.getId() == id && ((Doctor)doctor).getAppointmentType().getName().equals(type)){ //proverim i za tip
                        if(!clinics.contains(clinicService.findById(((Doctor)doctor).getClinic().getId()))){
                            clinics.add(clinicService.findById(((Doctor)doctor).getClinic().getId()));
                            //System.out.println(clinics.get(0).getName());
                        }

                    }
                }
                else
                {
                    if(doctor.getId() == id){ //proverim i za tip
                        if(!clinics.contains(clinicService.findById(((Doctor)doctor).getClinic().getId()))){
                            clinics.add(clinicService.findById(((Doctor)doctor).getClinic().getId()));
                            //System.out.println(clinics.get(0).getName());
                        }

                    }

                }

            }
        }


//        System.out.println(medicalStaffService.findByRole("doctor").get(0).getId());

        return clinics;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/api/get-search-doctors/{date}/{imeKlinike}/{tipPregleda}", method= RequestMethod.GET)
    public List<Doctor> getSearchDoctors(@PathVariable("date") String date, @PathVariable("imeKlinike") String imeKlinike, @PathVariable("tipPregleda") String tipPregleda){
//        System.out.println("usao565656565"+date);

        List<MedicalStaff> doctors = medicalStaffService.findByRole("doctor");
        List<Long> doctorsId = new ArrayList<>();
        //System.out.println(doctors);
        for (MedicalStaff doctor:doctors) {
            //System.out.println(doctor.getId());
            doctorsId.add(doctor.getId()); //id svih doktora
        }

        List<Long> doctoriKojiSuZauzeti = new ArrayList<>(); //zauzeti doktori za taj datum(njihov id)
        for(Appointment app:appointmentService.findByFinished(false)){
            if(app.getDate().split("T")[0].equals(date)){
//                System.out.println(app.getDoctor().getId());
                if(!doctoriKojiSuZauzeti.contains(app.getDoctor().getId())){
                    doctoriKojiSuZauzeti.add(app.getDoctor().getId());
                }
            }
        }

//        System.out.println(doctoriKojiSuZauzeti);

        if(doctoriKojiSuZauzeti.size() != 0){
            for (Long id:doctoriKojiSuZauzeti) {
                doctorsId.remove(id); //obrisem id doktora koji su zauzeti, ostanu samo slobodni u doctorsId
            }
        }
//        System.out.println(doctorsId);

        Clinic klinika = clinicService.findByName(imeKlinike);
        Set<Doctor> doctorsInClinic = klinika.getDoctors();

        List<Doctor> ret = new ArrayList<>();
        for(Doctor doc : doctorsInClinic){
            for(Long id : doctorsId){
                if(!tipPregleda.equals("-1")){
                    if(doc.getId() == id && doc.getAppointmentType().getName().equals(tipPregleda)){
                        ret.add(doc);
                    }
                }
                else{
                    if(doc.getId() == id){
                        ret.add(doc);
                    }
                }

            }
        }


        //System.out.println(ret);

        return ret;
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
        Clinic c = (Clinic) clinicService.findByName(name);
        if(c != null){
            c.setAddress(newClinic.getAddress());
            c.setDescription(newClinic.getDescription());
            c.setName(newClinic.getName());
            clinicService.save(c);
        }
        else{
            // Patient patient = new Patient(patientNovi.getUsername(), patientNovi.getPassword(), patientNovi.getFirstName(), patientNovi.getLastName(), patientNovi.getEmail(), patientNovi.getAddress(), patientNovi.getCity(), patientNovi.getCountry(), patientNovi.getMobileNumber(), patientNovi.getJmbg());

            // patientService.save(patient);
        }
        ClinicDTO clinicDTO=new ClinicDTO(c);
        return new ResponseEntity<>(clinicDTO, HttpStatus.OK);

    }

}
