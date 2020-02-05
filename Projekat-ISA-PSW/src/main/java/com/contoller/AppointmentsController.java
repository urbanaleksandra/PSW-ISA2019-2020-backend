package com.contoller;


import com.dto.AppointmentDTO;
import com.dto.AvailableHospitalRoomDTO;
import com.dto.CalendarEventsDTO;
import com.dto.ReservationHospitalRoomDTO2;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.model.*;
import com.model.RequestAppointment;
import com.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AppointmentsController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private RequestAppointmentService requestAppointmentService;

    @Autowired
    private SurgeryService surgeryService;

    @Autowired
    private ClinicAdministratorService clinicAdministratorService;

    @Autowired
    private HospitalRoomService hospitalRoomService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private MedicalRecordService medicalRecordService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private MedicalStaffService medicalStaffService;

    @CrossOrigin(origins = "http//localhost:4200")
    @RequestMapping(value = "/api/availableRoomsAppointment", method = RequestMethod.POST)
    public List<HospitalRoom> availableRooms(@RequestBody AppointmentDTO appointmentDTO) throws ParseException {
        System.out.println("USAO U AVAILABLE ROOMS");
        List<HospitalRoom> ret = new ArrayList<>();
        System.out.println(appointmentDTO);
        RequestAppointment appointment = null;
        try{
            appointment = requestAppointmentService.findById(appointmentDTO.getId());
        }catch (Exception e){
            return null;
        }

        Clinic clinic = appointment.getClinic();
        if(clinic==null)
        {
            Clinic c=clinicService.findById(appointmentDTO.getClinicDTO().getId());
            appointment.setClinic(c);
        }
        System.out.println("AAAAAAA");
        System.out.println(clinic.getId());
        //nalazim milisecs koje su vezane za moj zakazani pregled
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date date = dateFormat.parse(appointmentDTO.getDate());

        // prolazim kroz sve sale i gledam koja je slobodna u tom periodu
        List<HospitalRoom> allRooms = hospitalRoomService.findByClinicId(clinic.getId());
        for (HospitalRoom hospitalRoom:allRooms) {
            boolean nadjenaOperacijaKojaJeUTomTerminu = false;
            //gledam da li je zakazana neka operacija tad
            List<Surgery> roomSurgeries = surgeryService.findByHospitalId(hospitalRoom.getId());
            for (Surgery s:roomSurgeries) {
                if(nadjenaOperacijaKojaJeUTomTerminu == false){
                    //poredim datum moje operacije i datum operacije u ovom for-u
                    if(appointment.getDate().equals(s.getDate())){
                        nadjenaOperacijaKojaJeUTomTerminu = true;

                    }
                }
            }

            List<Appointment> appsRoom = this.appointmentService.findByHospitalRoomId(hospitalRoom.getId());

            for(Appointment appointment1:appsRoom){
                if(nadjenaOperacijaKojaJeUTomTerminu == false){
                    //poredim datum moje operacije i datum operacije u ovom for-u, ako je 0 onda su jednaki
                    if(appointment1.getDate().equals(appointment.getDate())){
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

    // cadmina prosledjujem kako bih znala u kojoj je on klinici,
    // i onda uzimam samo one operacije koje su vezane za tu kliniku
    @CrossOrigin(origins = "http//localhost:4200")
    @RequestMapping(value = "/api/appointments-res-rooms/{cadmin}", method = RequestMethod.GET)
    public List<RequestAppointment> getResRoom(@PathVariable String cadmin){
        System.out.println("usao");
        List<RequestAppointment> appointments = new ArrayList<>();
        List<RequestAppointment> ret = new ArrayList<RequestAppointment>();
        ClinicAdministrator ca;
        try{
            ca = clinicAdministratorService.findByUsername(cadmin);
        } catch (Exception e){
            System.out.println("nije ulogovan cadmin");
            return null;
        }
        if(ca != null) {
            appointments = requestAppointmentService.findByClinicId(ca.getClinic().getId());
            for (RequestAppointment a : appointments) {
                //uzimam one operacije kojima nije dodeljena sala
                if (a.getHospitalRoom() == null)
                    ret.add(a);


            }
        }
        return ret;
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/api/add-room-app", method=RequestMethod.POST,  produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Appointment> addApp(@RequestBody AppointmentDTO appointment) {
        Appointment appointment1 = new Appointment();
       appointment1.setDate(appointment.getDate());
        appointment1.setDescription(appointment.getDescription());
        appointment1.setDoctorUsername(appointment.getDoctorUsername());
        appointment1.setHospitalRoom(this.hospitalRoomService.findById(appointment.getRoomID()));
        appointment1.setDuration(appointment.getDuration());
        appointment1.setType(appointment.getType());
        appointment1.setPatient(appointment.getPatient());
        appointment1.setDoctor((Doctor) medicalStaffService.findByUsername(appointment.getDoctorUsername()));


        Patient pa = patientService.findByUsername(appointment.getPatient());
        System.out.println(pa.getUsername());
        Long paID = pa.getId();

        MedicalRecord mr = medicalRecordService.findByPatientId(paID);
//        mr.addRequestAppointment(appointment1);
//        medicalRecordService.save(mr);
//        System.out.println(mr.getId());
        appointment1.setMedicalRecord(mr);
        //Doctor doc= (Doctor) medicalStaffService.findByUsername(appointment.getDoctor().getUsername());
        //appointment1.setDoctor(doc);
        appointmentService.save(appointment1);

        try {
            emailService.sendPatientNotificaition7(appointment1,pa);
        }catch( Exception e ){
            System.out.println("nije poslata poruka");
        }

        requestAppointmentService.delete(requestAppointmentService.findById(appointment.getId()));
        return new ResponseEntity<>(appointment1, HttpStatus.OK);
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/api/add-requestApp", method=RequestMethod.POST,  produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<RequestAppointment> addRequestApp(@RequestBody AppointmentDTO appointment){

        RequestAppointment appointment1 = new RequestAppointment(appointment.getPatient(),appointment.getDate(),appointment.getDescription(),appointment.getDuration());
        //requestAppointmentService.save(appointment1);

        try {


            Patient pa = patientService.findByUsername(appointment.getPatient());
            System.out.println(pa.getUsername());
            Long paID = pa.getId();

            MedicalRecord mr = medicalRecordService.findByPatientId(paID);
//        mr.addRequestAppointment(appointment1);
//        medicalRecordService.save(mr);
//        System.out.println(mr.getId());
            appointment1.setMedicalRecord(mr);
        } catch ( Exception e ){
            System.out.println("Pacijent je null");
        }
        requestAppointmentService.save(appointment1);


        try {
            emailService.sendNotificaitionAsync3();
        }catch( Exception e ){
            System.out.println("nije poslata poruka");
        }

        return new ResponseEntity<>(appointment1, HttpStatus.OK);

    }

    // za brze preglede
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/api/add-requestAppFAST/{username}", method=RequestMethod.POST,  produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<RequestAppointment> addRequestAppo(@RequestBody AppointmentDTO appointment,@PathVariable String username){

        RequestAppointment appointment1 = new RequestAppointment(appointment.getPatient(),appointment.getDate(),appointment.getDescription(),appointment.getDuration());
        //requestAppointmentService.save(appointment1);
        ClinicAdministrator ca=clinicAdministratorService.findByUsername(username);
        Clinic c=clinicService.findById(ca.getClinic().getId());
        appointment1.setClinic(c);
        requestAppointmentService.save(appointment1);

        return new ResponseEntity<>(appointment1, HttpStatus.OK);

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/api/add-requestApp-from-patient", method=RequestMethod.POST,  produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<RequestAppointment> addRequestAppFromPatient(@RequestBody AppointmentDTO appointment){

        RequestAppointment appointment1 = new RequestAppointment(appointment.getPatient(),appointment.getDate(),appointment.getDescription(),appointment.getDuration());
        //requestAppointmentService.save(appointment1);

        Patient pa = patientService.findByUsername(appointment.getPatient());
        System.out.println(pa.getUsername());
        Long paID = pa.getId();

        MedicalRecord mr = medicalRecordService.findByPatientId(paID);
//        mr.addRequestAppointment(appointment1);
//        medicalRecordService.save(mr);
//        System.out.println(mr.getId());
        appointment1.setMedicalRecord(mr);
        appointment1.setDoctorUsername(appointment.getDoctorUsername());
        appointment1.setType(appointment.getType());
        requestAppointmentService.save(appointment1);


        try {
            emailService.sendNotificaitionAsync4();
        }catch( Exception e ){
            System.out.println("nije poslata poruka");
        }

        return new ResponseEntity<>(appointment1, HttpStatus.OK);

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/getAppointmentRequests", method= RequestMethod.GET)
    public List<RequestAppointment> getAppointmentReq(){

        return requestAppointmentService.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/getAppointments/{username}", method= RequestMethod.GET)
    public List<Appointment> getAppointments(@PathVariable String username){

        List<Appointment> ret = new ArrayList<>();
        List<Appointment> sviPregledi = appointmentService.findAll();
        for(Appointment a : sviPregledi){
            if(a.isFinished()){
                ret.add(a);
            }
        }

        return ret;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/appointments", method= RequestMethod.GET)
    public List<Appointment> getAppointments2(){

        return appointmentService.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/getAppointmentsMR/{username}", method= RequestMethod.GET)
    public List<Appointment> getAppointmentsMR(@PathVariable String username){

        return appointmentService.findAll();
    }

    //preuzimanje svih appointmenta za kalendar
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/api/getAllAppointments", method= RequestMethod.GET)
    public List<CalendarEventsDTO> getAllAppointments() throws ParseException {
        List<Appointment> lista = appointmentService.findAll();
        List<CalendarEventsDTO> eventsDTOS = new ArrayList<CalendarEventsDTO>();

        for (Appointment app: lista) {
            Patient patient = patientService.findByUsername(app.getPatient());
            String title = app.getDescription() + "\n" + patient.getFirstName() + " " + patient.getLastName();
            String color = "";
            if(app.getType().equals("appointment"))
                color = "purple";
            else
                color = "green";

            //dodavanje duration
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Date date = dateFormat.parse(app.getDate());
            long millis = date.getTime();
            millis += app.getDuration() * 60 * 60 * 1000;
            String endDate = dateFormat.format(millis);

            CalendarEventsDTO eventsDTO = new CalendarEventsDTO(title, app.getDate(), endDate, app.getId(), color);
            eventsDTOS.add(eventsDTO);
        }
        return eventsDTOS;
    }


    @CrossOrigin(origins = "http//localhost:4200")
    @RequestMapping(value="/api/available-room-other-date-Appointment", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    private AvailableHospitalRoomDTO availableRoomOtherDate(@RequestBody AppointmentDTO appointmentDTO) throws ParseException {
        AvailableHospitalRoomDTO ret = new AvailableHospitalRoomDTO();
        System.out.println(appointmentDTO);
        RequestAppointment requestAppointment = requestAppointmentService.findById(appointmentDTO.getId());
        //sve sale u toj klinici
        List<HospitalRoom> rooms = hospitalRoomService.findByClinicId(requestAppointment.getClinic().getId());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date date = dateFormat.parse(requestAppointment.getDate());
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
    private void systemReservation2() throws ParseException, InterruptedException {
        System.out.println("usao u sheduled fun");
        List<RequestAppointment> allRequestAppointments = requestAppointmentService.findAll();
        List<RequestAppointment> requestsWithoutRoom = new ArrayList<>();
        for (RequestAppointment requestAppointment:allRequestAppointments) {
            if(requestAppointment.getHospitalRoom() == null)
                requestsWithoutRoom.add(requestAppointment);
        }

        for (RequestAppointment s:requestsWithoutRoom) {
            RequestAppointment req = requestAppointmentService.findById(s.getId());
            List<HospitalRoom> rooms = hospitalRoomService.findByClinicId(req.getClinic().getId());

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Date date = dateFormat.parse(req.getDate());
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
                            req.setHospitalRoom(room);
                            req.setDate(newDate);
                            Appointment appointment1=new Appointment();

                            appointment1.setDate(req.getDate());
                            appointment1.setDescription(req.getDescription());
                            appointment1.setDoctorUsername(req.getDoctorUsername());
                            appointment1.setDuration(req.getDuration());
                            appointment1.setType(req.getType());
                            appointment1.setPatient(req.getPatient());
                            appointment1.setHospitalRoom(req.getHospitalRoom());

                            room.getAppointments().add(appointment1);
                            Appointment appointment = appointmentService.save(appointment1);
                            HospitalRoom saveHR = hospitalRoomService.save(room);
                            Patient patient = patientService.findByUsername(req.getPatient());
                            try{
                                emailService.sendPatientNotificaition2(appointment1, patient);
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

    //za fast appointments
    @CrossOrigin(origins = "http//localhost:4200")
    @RequestMapping(value="/api/add-room-to-appointmentF", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public void addRoomToFastApp(@RequestBody ReservationHospitalRoomDTO2 reservationHospitalRoomDTO) throws InterruptedException {
        System.out.println(reservationHospitalRoomDTO);
        HospitalRoom hospitalRoom = this.hospitalRoomService.findById(reservationHospitalRoomDTO.getAppointmentDTO().getRoomID());
        RequestAppointment req = this.requestAppointmentService.findById(reservationHospitalRoomDTO.getAppointmentDTO().getId());
        req.setHospitalRoom(hospitalRoom);
        req.setDate(reservationHospitalRoomDTO.getAppointmentDTO().getDate());

        req.setDoctor(this.doctorService.findById(reservationHospitalRoomDTO.getDoctor()));


        //prebacujem iz request appointment u appointment
        Appointment appointment1=new Appointment();

        appointment1.setDate(req.getDate());
        appointment1.setDescription(req.getDescription());
        appointment1.setDoctorUsername(req.getDoctorUsername());
        appointment1.setDuration(req.getDuration());
        appointment1.setType("neki tip");
        appointment1.setPatient(null);
        appointment1.setHospitalRoom(req.getHospitalRoom());
        appointment1.setDoctor(req.getDoctor());

        Appointment a = this.appointmentService.save(appointment1);


            Doctor doctor = this.doctorService.findById(reservationHospitalRoomDTO.getDoctor());
            doctor.getAppointments().add(a);
            Doctor d = this.doctorService.save(doctor);
           /* try {
                emailService.sendDoctorNotificaition(a, doctor);
            }catch (Exception e){
                System.out.println("email fail");
            }*/



       hospitalRoom.getAppointments().add(a);
        HospitalRoom hospitalRoom1 = this.hospitalRoomService.save(hospitalRoom);
    }


}
