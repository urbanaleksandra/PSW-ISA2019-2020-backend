package com.contoller;

import com.dto.AvailableHospitalRoomDTO;
import com.dto.ReservationHospitalRoomDTO;
import com.dto.SurgeryDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.model.*;
import com.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private EmailService email;

    @Autowired
    private PatientService patientService;

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
                doctor = doctorService.findByUsername(surgeryDTO.getDoctorSurgery());
                System.out.println(doctor.getUsername());
                clinic = doctor.getClinic();
                System.out.println(clinic.getAddress());
            }
            catch (Exception e){

            }
            if(doctor != null) {
                Set<Surgery> surgeries = doctor.getSurgeries();
                Set<Appointment> appointments = doctor.getAppointments();
                boolean available = true;
                for (Surgery s:surgeries) {
                    if(available) {
                        if (s.getDate().equals(surgery.getDate())) {
                            available = false;
                        }
                    }
                }
                for(Appointment a:appointments){
                    if(available) {
                        if (a.getDate().equals(surgery.getDate())) {
                            available = false;
                        }
                    }
                }
                surgery.setClinic(clinic);
                surgery.setDuration(2);
                if(available)
                    surgery.getDoctor().add(doctor);
                Surgery surgeriesave = surgeryService.save(surgery);

                try{
                    Patient patient = patientService.findByUsername(surgeryDTO.getPatient());
                    MedicalRecord mr = medicalRecordService.findByPatientId(patient.getId());
                    mr.getSurgeries().add(surgeriesave);
                    MedicalRecord mr2 = medicalRecordService.save(mr);
                }catch(Exception e){
                    System.out.println("ne moze da se doda operacija u zdravstveni karton");
                }
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
        System.out.println("USAO U AVAILABLE ROOMS");
        List<HospitalRoom> ret = new ArrayList<>();
        System.out.println(surgeryDTO);
        Surgery surgery = null;
        try{
            surgery = surgeryService.findById(surgeryDTO.getId());
        }catch (Exception e){
            return null;
        }

        Clinic clinic = surgery.getClinic();
        //nalazim milisecs koje su vezane za moj zakazani pregled
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date date = dateFormat.parse(surgeryDTO.getDate());

        // prolazim kroz sve sale i gledam koja je slobodna u tom periodu
        List<HospitalRoom> allRooms = hospitalRoomService.findByClinicId(surgery.getClinic().getId());
        for (HospitalRoom hospitalRoom:allRooms) {
            boolean nadjenaOperacijaKojaJeUTomTerminu = false;
            //gledam da li je zakazana neka operacija tad
            List<Surgery> roomSurgeries = surgeryService.findByHospitalId(hospitalRoom.getId());
            for (Surgery s:roomSurgeries) {
                if(nadjenaOperacijaKojaJeUTomTerminu == false){
                //poredim datum moje operacije i datum operacije u ovom for-u
                    if(surgery.getDate().equals(s.getDate())){
                        nadjenaOperacijaKojaJeUTomTerminu = true;

                    }
                }
            }

            List<Appointment> appsRoom = this.appointmentService.findByHospitalRoomId(hospitalRoom.getId());

            for(Appointment appointment:appsRoom){
                if(nadjenaOperacijaKojaJeUTomTerminu == false){
                    //poredim datum moje operacije i datum operacije u ovom for-u, ako je 0 onda su jednaki
                    if(surgery.getDate().equals(appointment.getDate())){
                        //moram sad da proverim satnice dal se poklapaju
                            nadjenaOperacijaKojaJeUTomTerminu = true;

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
    public List<Doctor> getAvailableDoctors(@RequestBody SurgeryDTO surgeryDTO) throws ParseException {
        System.out.println("AVAILABLE DOCTORS " + surgeryDTO);
        Surgery surgery  = surgeryService.findById(surgeryDTO.getId());
        List<Doctor> doctors = this.doctorService.findByClinicId(surgery.getClinic().getId());
        List<Doctor> availableDoctors = new ArrayList<>();
        for (Doctor doctor:doctors) {

            boolean available = true;

            for(Surgery s: doctor.getSurgeries()){
                if(surgeryDTO.getDate().equals(s.getDate())){
                    available = false;
                }
            }
            System.out.println("PREGLEDI " + doctor.getAppointments().size());


            for (Appointment appointment:doctor.getAppointments()) {
                if(available == true) {
                    if (surgeryDTO.getDate().equals(appointment.getDate())) {
                        available = false;
                        System.out.println("PRONASAO PREGLED KOJI JE U TOM PERIODU "+ appointment.getDate());

                    }
                }
            }

            if(available)
                availableDoctors.add(doctor);
        }

        return availableDoctors;
    }

    @CrossOrigin(origins = "http//localhost:4200")
    @RequestMapping(value="/api/add-room-to-surgery", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addRoomToSurgery(@RequestBody ReservationHospitalRoomDTO reservationHospitalRoomDTO) throws InterruptedException {
            try{
               String text = surgeryService.AddRoomToSurgery(reservationHospitalRoomDTO);
               if(text.equals("greska"))
                   return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

                return new ResponseEntity<>(HttpStatus.OK);
            }catch (Exception e){
                System.out.println("soba je vec zakazana");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
    }


    @CrossOrigin(origins = "http//localhost:4200")
    @RequestMapping(value="/api/available-room-other-date", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    private AvailableHospitalRoomDTO availableRoomOtherDate(@RequestBody SurgeryDTO surgeryDTO) throws ParseException {
        AvailableHospitalRoomDTO ret = new AvailableHospitalRoomDTO();
        System.out.println(surgeryDTO);
        Surgery surgery = surgeryService.findById(surgeryDTO.getId());
        //sve sale u toj klinici
        List<HospitalRoom> rooms = hospitalRoomService.findByClinicId(surgery.getClinic().getId());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date date = dateFormat.parse(surgery.getDate());
        long startSurgery = date.getTime();


        boolean nadjenaSoba = false;
        while(nadjenaSoba == false) {
            startSurgery = startSurgery + 2 * 60 * 60 * 1000;
            String newDate = dateFormat.format(startSurgery);
            for (HospitalRoom room:rooms) {
                if (nadjenaSoba == false) {
                    System.out.println("soba id + " + room.getId());
                    List<Surgery> surgeries = surgeryService.findByHospitalId(room.getId());
                    List<Appointment> appointments = appointmentService.findByHospitalRoomId(room.getId());

                    nadjenaSoba = checkTime(newDate, appointments, surgeries);
                    if(nadjenaSoba){
                        ret.setDate(newDate);
                        ret.setId(room.getId());
                        ret.setRoom_num(room.getRoom_number());
                        ret.setName(room.getName());
                    }
                }

            }


        }

        return ret;
    }

    private boolean checkTime(String date, List<Appointment> appointments, List<Surgery> surgeries){
        boolean available = true;
        System.out.println("checktime for date: " + date);
        for(Surgery surgery:surgeries) {
            if(available) {
                System.out.println("surg + " + surgery.getDate());
                if (surgery.getDate().equals(date)) {
                    System.out.println("surgery available false " + surgery.getDate());
                    available = false;
                }
            }
        }

        for(Appointment appointment:appointments){
            if(available){
                System.out.println("app + " + appointment.getDate());
                if(appointment.getDate().equals(date)){
                    System.out.println("app available false " + appointment.getDate());
                    available = false;
                }
            }
        }
        return available;
    }

    @Scheduled(cron  = "${greeting.cron}")
    private void systemReservation() throws ParseException, InterruptedException {
        System.out.println("usao u sheduled fun");
        List<Surgery> allSurgeries = surgeryService.findAll();
        List<Surgery> surgeriesWithoutRoom = new ArrayList<>();
        for (Surgery surgery:allSurgeries) {
            if(surgery.getHospitalRoom() == null)
                surgeriesWithoutRoom.add(surgery);
        }

        for (Surgery s:surgeriesWithoutRoom) {
            Surgery surgery = surgeryService.findById(s.getId());
            List<HospitalRoom> rooms = hospitalRoomService.findByClinicId(surgery.getClinic().getId());

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Date date = dateFormat.parse(surgery.getDate());
            long startSurgery = date.getTime();

            boolean nadjenaSoba = false;
            while(nadjenaSoba == false) {
                String newDate = dateFormat.format(startSurgery);
                for (HospitalRoom room:rooms) {
                    if (nadjenaSoba == false) {
                        System.out.println("soba id + " + room.getId());
                        List<Surgery> surgeries = surgeryService.findByHospitalId(room.getId());
                        List<Appointment> appointments = appointmentService.findByHospitalRoomId(room.getId());

                        nadjenaSoba = checkTime(newDate, appointments, surgeries);
                        if(nadjenaSoba){
                            s.setHospitalRoom(room);
                            s.setDate(newDate);
                            room.getSurgeries().add(s);
                            Surgery saveS = surgeryService.save(s);
                            HospitalRoom saveHR = hospitalRoomService.save(room);
                            Patient patient = patientService.findByUsername(s.getPatient());
                            try{
                                email.sendPatientNotificaition(s, patient);
                            }catch (Exception e){
                                System.out.println("email fail");
                            }

                        }else{
                            startSurgery = startSurgery + 2 * 60 * 60 * 1000;
                        }
                    }
                }
            }
        }
    }
}
