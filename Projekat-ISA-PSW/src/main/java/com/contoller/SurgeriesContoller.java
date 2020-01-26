package com.contoller;

import com.dto.SurgeryDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.model.*;
import com.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.SeparatorUI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

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

    @Autowired
    private HospitalRoomService hospitalRoomService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private MedicalStaffService medicalStaffService;

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/getSurgeries/{username}", method= RequestMethod.GET)
    public List<Surgery> getSurgeries(@PathVariable String username){

        return surgeryService.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/api/new-surgery", method= RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<SurgeryDTO> addSurgery(@RequestBody SurgeryDTO surgeryDTO){
            System.out.println(surgeryDTO);
            Surgery surgery = new Surgery();
            surgery.setDate(surgeryDTO.getDate());
            surgery.setPatient(surgeryDTO.getPatient());
            surgery.setDescription(surgeryDTO.getDescription());
            Doctor doctor = null;
            Clinic clinic = null;
            try {
                doctor = doctorService.findByUsername(surgeryDTO.getDoctor());
                System.out.println(doctor.getUsername());
                clinic = doctor.getClinic();
                System.out.println(clinic.getAddress());
            }
            catch (Exception e){

            }
            if(doctor != null) {
                surgery.setClinic(clinic);
                Surgery surgeries = surgeryService.save(surgery);
            }
        return new ResponseEntity<>(surgeryDTO, HttpStatus.OK);
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
        if(ca != null) {
            surgeries = surgeryService.findByClinicId(ca.getClinic().getId());
            for (Surgery s : surgeries) {
                //uzimam one operacije kojima nije dodeljena sala
                if (s.getHospitalRoom() == null)
                    ret.add(s);


            }
        }
        return ret;
    }

    @CrossOrigin(origins = "http//localhost:4200")
    @RequestMapping(value = "/api/availableRooms", method = RequestMethod.POST)
    public List<HospitalRoom> availableRooms(@RequestBody SurgeryDTO surgeryDTO) throws ParseException {
        List<HospitalRoom> ret = new ArrayList<>();
        System.out.println(surgeryDTO);

        //nalazim milisecs koje su vezane za moj zakazani pregled
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date date = dateFormat.parse(surgeryDTO.getDate());
        long startSurgery = date.getTime();
        long endSurgery =  startSurgery +  surgeryDTO.getDuration() * 60 * 60 * 1000;
        System.out.print(startSurgery);
        System.out.print(endSurgery);
        //datum bez vremena
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        Date date2 = dateFormat2.parse(surgeryDTO.getDate());


        // prolazim kroz sve sale i gledam koja je slobodna u tom periodu
        List<HospitalRoom> allRooms = hospitalRoomService.findAll();
        for (HospitalRoom hospitalRoom:allRooms) {
            boolean nadjenaOperacijaKojaJeUTomTerminu = false;
            //gledam da li je zakazana neka operacija tad
            List<Surgery> roomSurgeries = surgeryService.findByHospitalId(hospitalRoom.getId());
            for (Surgery s:roomSurgeries) {
                if(nadjenaOperacijaKojaJeUTomTerminu == false){
                //poredim datum moje operacije i datum operacije u ovom for-u, ako je 0 onda su jednaki
                    if(date2.compareTo(dateFormat2.parse(s.getDate())) == 0){
                        //moram sad da proverim satnice dal se poklapaju
                        Date dateS = dateFormat.parse(s.getDate());
                        long startSurgeryS = dateS.getTime();
                        long endSurgeryS =  startSurgeryS +  s.getDuration() * 60 * 60 * 1000;
                        System.out.print(startSurgeryS);
                        System.out.print(endSurgeryS);
                        if(!((startSurgeryS < startSurgery && endSurgeryS < startSurgery) ||
                                (startSurgeryS > endSurgery && endSurgeryS > endSurgery))){
                            nadjenaOperacijaKojaJeUTomTerminu = true;
                        }
                    }
                }
            }

            if(!nadjenaOperacijaKojaJeUTomTerminu)
                ret.add(hospitalRoom);
            Set<Appointment> roomAppointments = hospitalRoom.getAppointments();
        }

        return ret;
    }

    @CrossOrigin(origins = "http//localhost:4200")
    @RequestMapping(value = "/api/availableDoctors", method = RequestMethod.POST)
    public List<Doctor> getAvailableDoctors(@RequestBody SurgeryDTO surgeryDTO){
        List<Doctor> ret = new ArrayList<>();
        System.out.println(surgeryDTO);
        List<Doctor> doctors = this.doctorService.findAll();
        List<Surgery> surgeries = this.surgeryService.findAll();
        for (Doctor doctor:doctors) {
            System.out.println("Doctor id: " + doctor.getId() + "num of surgeries: " + doctor.getSurgeries().size());
        }
        for (Surgery surgery:surgeries) {
            System.out.println("Surgery id: " + surgery.getId() + "num of doctors: " + surgery.getDoctor().size());
        }
        return ret;
    }
}
