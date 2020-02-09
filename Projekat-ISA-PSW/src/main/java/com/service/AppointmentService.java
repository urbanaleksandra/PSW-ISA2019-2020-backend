package com.service;

import com.dto.AppointmentDTO;
import com.model.*;
import com.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.validation.ValidationException;
import java.util.List;
import java.util.Optional;


@Service
public class AppointmentService implements AppointmentServiceInterface{

    @Autowired
    public AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private RequestAppointmentService requestAppointmentService;

    @Autowired
    private HospitalRoomService hospitalRoomService;

    @Autowired
    private MedicalStaffService medicalStaffService;


    @Autowired
    private PatientService patientService;

    @Autowired
    private MedicalRecordService medicalRecordService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ClinicAdministratorService clinicAdministratorService;

    @Autowired
    private SurgeryService surgeryService;

    @Autowired
    private ClinicService clinicService;


    public Appointment save(Appointment appointment){
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> findAll(){
        return appointmentRepository.findAll();
    }

    public List<Appointment> findByFinished(Boolean finished){ return appointmentRepository.findByFinished(finished); }


    public Appointment findByDate(String date){ return appointmentRepository.findByDate(date); }

    public List<Appointment> findByHospitalRoomId(Long id) { return appointmentRepository.findByHospitalRoomId(id); }

    public Appointment findById(Long id) { return appointmentRepository.findById(id).get();}

    public Appointment setFinished(Appointment app) {
        System.out.println("usao u setFinished");
        app.setFinished(true);
        Appointment savedApp = appointmentRepository.save(app);
        return savedApp;
    }

    //transakcija
    @Transactional(rollbackFor = {RuntimeException.class},readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED)
    public Appointment acceptAppointment(AppointmentDTO appointment){
        System.out.println("USAO1");
        Appointment appointment1 = new Appointment();
        appointment1.setDate(appointment.getDate());
        appointment1.setDescription(appointment.getDescription());
        appointment1.setDoctorUsername(appointment.getDoctorUsername());
        appointment1.setHospitalRoom(this.hospitalRoomService.findById(appointment.getRoomID()));
        appointment1.setDuration(appointment.getDuration());
        appointment1.setType(appointment.getType());
        appointment1.setPatient(appointment.getPatient());
        appointment1.setDoctor((Doctor) medicalStaffService.findByUsername(appointment.getDoctorUsername()));
        appointment1.setPrice(appointment.getPrice());
        System.out.println("USAO2");
        Patient pa = patientService.findByUsername(appointment.getPatient());
        System.out.println(pa.getUsername());
        Long paID = pa.getId();

        MedicalRecord mr = medicalRecordService.findByPatientId(paID);
        appointment1.setMedicalRecord(mr);
        appointmentService.save(appointment1);

        try {
            emailService.sendPatientNotificaition7(appointment1,pa);
        }catch( Exception e ){
            System.out.println("nije poslata poruka");
        }
        System.out.println(appointment.getId());
        RequestAppointment ra= requestAppointmentService.findById(appointment.getId());
        System.out.println(ra.getId());
        requestAppointmentService.delete(ra);

        return appointment1;

    }

    //transakcija 3.12-brzi pregled
    @Transactional(rollbackFor = {RuntimeException.class},readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED)
    public Appointment schedule(AppointmentDTO appointment){

        Appointment appointment1 = appointmentRepository.findById(appointment.getId()).get();

        Patient patient = patientService.findByUsername(appointment.getPatient());
        System.out.println(patient);
        MedicalRecord mr = medicalRecordService.findByPatientId(patient.getId());
        appointment1.setMedicalRecord(mr);
        appointment1.setPatient(appointment.getPatient());
        System.out.println(appointment1.getPatient());
        try{
            appointmentRepository.save(appointment1);
        }
        catch( Exception e ){
            System.out.println("Pukao kod transakcije");
            return null;
        }
        try {
            emailService.sendNotificaitionAsync4(patient);
        }catch( Exception e ){
            System.out.println("nije poslata poruka");
        }


        return appointment1;

    }

    //transakcija
    @Transactional(rollbackFor = {RuntimeException.class},readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public RequestAppointment addRequestApp(AppointmentDTO appointment){

        List<RequestAppointment> requestAppointments = requestAppointmentService.findAll();
        for(RequestAppointment reqApp : requestAppointments){
            if(reqApp.getDate().equals(appointment.getDate()) && reqApp.getDoctor().getUsername().equals(appointment.getDoctorUsername())){
                return null; //new ResponseEntity<>(reqApp, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        RequestAppointment appointment1 = new RequestAppointment(appointment.getPatient(),appointment.getDate(),appointment.getDescription(),appointment.getDuration());

        Patient pa = patientService.findByUsername(appointment.getPatient());
        Long paID = pa.getId();

        MedicalRecord mr = medicalRecordService.findByPatientId(paID);
        appointment1.setMedicalRecord(mr);
        appointment1.setDoctorUsername(appointment.getDoctorUsername());
        appointment1.setType(appointment.getType());

        Doctor doctor = doctorService.findByUsername(appointment.getDoctorUsername());
        appointment1.setDoctor(doctor);
        appointment1.setClinic(doctor.getClinic());

        requestAppointmentService.save(appointment1);


        try {
            ClinicAdministrator ca = clinicAdministratorService.findByClinicId(doctor.getClinic().getId()).get(0);
            emailService.sendNotificaitionAsync8(ca);
        }catch( Exception e ){
            System.out.println("nije poslata poruka");
        }

        return appointment1;

    }


    public Appointment findOne(Long id) {

        Optional<Appointment> appointment = appointmentRepository.findById(id);

        if(appointment.isPresent()) {
            return appointment.get();
        }
        else {
            throw new ValidationException("Appointment does not exist!");
        }
    }


    public List<HospitalRoom> availableRooms(AppointmentDTO appointmentDTO) throws ParseException {
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



}
