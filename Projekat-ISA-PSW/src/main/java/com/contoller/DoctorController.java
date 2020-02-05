package com.contoller;

import com.dto.PatientRatedClinicDTO;
import com.dto.PatientRatedDoctorDTO;
import com.model.*;
import com.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientRatedDoctorService patientRatedDoctorService;

    @Autowired
    private MedicalStaffService medicalStaffService;

    @Autowired
    private PatientRatedClinicService patientRatedClinicService;

    @Autowired
    private ClinicService clinicService;

    @CrossOrigin(origins = "http://localhost:4200") //ne koristim ovu funkciju, al mozda ce zatrebati
    @RequestMapping(value="/getDoctorsForRate/{username}", method= RequestMethod.GET)
    public List<Doctor> getDoctorsForRate(@PathVariable String username){

        List<Appointment> pregledi = appointmentService.findAll();
        List<Doctor> doctors = new ArrayList<>();
        for(Appointment app : pregledi){
            if(app.getPatient() != null){
                if(app.getPatient().equals(username) && app.isFinished()){
                    doctors.add(app.getDoctor());
                    //System.out.println(app.getDoctor().getFirstName());
                }
            }

        }

        return doctors;
    }

    @CrossOrigin(origins = "http://localhost:4200") //ovde dobijam doktore koji se ocenjuju tj. patientRatedDoctor
    @RequestMapping(value="/getRate/{username}", method= RequestMethod.GET)
    public List<PatientRatedDoctor> getRate(@PathVariable("username") String username){

        List<Appointment> pregledi = appointmentService.findAll();
        List<Doctor> doctors = new ArrayList<>();
        List<Doctor> doctorsFromPrd = new ArrayList<>();
        List<PatientRatedDoctor> ret = new ArrayList<>();


        for(Appointment app : pregledi){
            if(app.getPatient() != null){
                if(app.getPatient().equals(username) && app.isFinished()){
                    doctors.add(app.getDoctor());
                }
            }

        }

        Patient patient = patientService.findByUsername(username);

        List<PatientRatedDoctor> prd = patientRatedDoctorService.findAll();

        for(PatientRatedDoctor p1 : prd){
            if(p1.getPatient() != null){
                if(p1.getPatient().getUsername().equals(username)){
                    doctorsFromPrd.add(p1.getDoctor());
                }
            }

        }

        for(Doctor doc : doctors){
            if(!doctorsFromPrd.contains(doc)){
                PatientRatedDoctor noviPrd = new PatientRatedDoctor();
                noviPrd.setOcena(0);
                noviPrd.setPatient(patient);
                noviPrd.setDoctor(doc);
                patientRatedDoctorService.save(noviPrd);
            }
        }

        List<PatientRatedDoctor> prd1 = patientRatedDoctorService.findAll();
        for(PatientRatedDoctor p : prd1){
            //System.out.println(p.getPatient().getUsername());
            //System.out.println(p.getDoctor().getFirstName());
            if(p.getPatient().getUsername().equals(username)){
                ret.add(p);
                //System.out.println(p);
            }
        }
        return ret;
    }

    @CrossOrigin(origins = "http://localhost:4200") //vrati sve PatientRatedClinic koje je pacijent(username) ocenio
    @RequestMapping(value="/getClinicsForRate/{username}", method= RequestMethod.GET)
    public List<PatientRatedClinic> getClinicsForRate(@PathVariable("username") String username){

        List<Appointment> pregledi = appointmentService.findAll();
        List<Clinic> clinics = new ArrayList<>();
        List<Clinic> clinicsFromPrclinic = new ArrayList<>();
        List<PatientRatedClinic> ret = new ArrayList<>();
        Patient patient = patientService.findByUsername(username);


        for(Appointment app : pregledi){
            if(app.getPatient() != null){
                if(app.getPatient().equals(username) && app.isFinished()){
                    clinics.add(app.getDoctor().getClinic());
                }
            }

        }

        List<PatientRatedClinic> prclinic = patientRatedClinicService.findAll();

        for(PatientRatedClinic prclin : prclinic){
            if(prclin.getPatient() != null){
                if(prclin.getPatient().getUsername().equals(username)){
                    clinicsFromPrclinic.add(prclin.getClinic());
                }
            }

        }

        for(Clinic c : clinics){
            if(!clinicsFromPrclinic.contains(c)){
                PatientRatedClinic noviPrclinic = new PatientRatedClinic();
                noviPrclinic.setOcena(0);
                noviPrclinic.setPatient(patient);
                noviPrclinic.setClinic(c);
                patientRatedClinicService.save(noviPrclinic);
            }
        }

        List<PatientRatedClinic> prclinic1 = patientRatedClinicService.findAll();

        for(PatientRatedClinic p : prclinic1){
            //System.out.println(p.getPatient().getUsername());
            //System.out.println(p.getDoctor().getFirstName());
            if(p.getPatient().getUsername().equals(username)){
                ret.add(p);
                //System.out.println(p);
            }
        }


        return ret;
    }

    @CrossOrigin(origins = "http://localhost:4200") //promena ocene doktorima
    @RequestMapping(value="/rateChange", method= RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<PatientRatedDoctorDTO> rateChange(@RequestBody PatientRatedDoctorDTO patientRatedDoctorDTO){
        //System.out.println(patientRatedDoctorDTO);

        List<PatientRatedDoctor> prd = patientRatedDoctorService.findAll();
        Doctor doctor = (Doctor) medicalStaffService.findByUsername(patientRatedDoctorDTO.getDoctorUsername());
        int suma = 0;
        int kolikoIhIma = 0;

        for(PatientRatedDoctor p : prd){
            if(p.getPatient().getUsername().equals(patientRatedDoctorDTO.getPatientUsername())){
                if(p.getDoctor().getUsername().equals(patientRatedDoctorDTO.getDoctorUsername())){
                    p.setOcena(patientRatedDoctorDTO.getOcena());
                    patientRatedDoctorService.save(p);
                }
            }
        }

        for(PatientRatedDoctor p : prd) {
            if(p.getDoctor().getUsername().equals(patientRatedDoctorDTO.getDoctorUsername())){
                suma += p.getOcena();
                kolikoIhIma++;
            }
        }

        doctor.setReview(suma/kolikoIhIma);
        medicalStaffService.save(doctor);

        return new ResponseEntity<>(patientRatedDoctorDTO, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200") //promena ocene doktorima
    @RequestMapping(value="/rateChangeClinic", method= RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<PatientRatedClinicDTO> rateChangeClinic(@RequestBody PatientRatedClinicDTO patientRatedClinicDTO){
        //System.out.println(patientRatedDoctorDTO);

        List<PatientRatedClinic> prclinic = patientRatedClinicService.findAll();
        Clinic clinic = clinicService.findByName(patientRatedClinicDTO.getClinicName());
        int suma = 0;
        int kolikoIhIma = 0;

        for(PatientRatedClinic p : prclinic){
            if(p.getPatient().getUsername().equals(patientRatedClinicDTO.getPatientUsername())){
                if(p.getClinic().getName().equals(patientRatedClinicDTO.getClinicName())){
                    p.setOcena(patientRatedClinicDTO.getOcena());
                    patientRatedClinicService.save(p);
                }
            }
        }

        for(PatientRatedClinic p : prclinic) {
            if(p.getClinic().getName().equals(patientRatedClinicDTO.getClinicName())){
                suma += p.getOcena();
                kolikoIhIma++;
            }
        }

        clinic.setRating(suma/kolikoIhIma);
        clinicService.save(clinic);

        return new ResponseEntity<>(patientRatedClinicDTO, HttpStatus.OK);
    }


}
