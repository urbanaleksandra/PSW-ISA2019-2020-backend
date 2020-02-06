package com.service;

import com.dto.AppointmentDTO;
import com.model.Appointment;
import com.model.Doctor;
import com.model.Patient;
import com.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class AppointmentService implements AppointmentServiceInterface{

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private MedicalRecordService medicalRecordService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private EmailService emailService;


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
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public Appointment schedule(AppointmentDTO appointment){

        //radim pesimisticko zakljucavanje(appointment repository) jer instanciram novi pregled
        Appointment appointment1 = appointmentRepository.findByDate(appointment.getDate()); //new Appointment(appointment.getId(), appointment.getPatient(), appointment.getDate(), appointment.getDescription(), appointment.getDuration());
//        appointment1.setType(appointment.getType());
//        appointment1.setDoctorUsername(appointment.getDoctorUsername());
//        Doctor doctor = doctorService.findByUsername(appointment.getDoctorUsername());
//
//        appointment1.setDoctor(doctor);

        Patient patient = patientService.findByUsername(appointment.getPatient());
        appointment1.setMedicalRecord(medicalRecordService.findByPatientId(patient.getId()));
        appointment1.setPatient(appointment.getPatient());

        appointmentRepository.save(appointment1);

        try {
            emailService.sendNotificaitionAsync4();
        }catch( Exception e ){
            System.out.println("nije poslata poruka");
        }

        return appointment1;

    }


}
