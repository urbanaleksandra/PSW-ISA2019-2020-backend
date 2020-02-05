package com.service;

import com.model.Appointment;
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


    public Appointment save(Appointment appointment){
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> findAll(){
        return appointmentRepository.findAll();
    }

    public List<Appointment> findByFinished(Boolean finished){ return appointmentRepository.findByFinished(finished); }

    public List<Appointment> findByDate(String date){ return appointmentRepository.findByDate(date); }

    public List<Appointment> findByHospitalRoomId(Long id) { return appointmentRepository.findByHospitalRoomId(id); }

    public Appointment findById(Long id) { return appointmentRepository.findById(id).get();}

    public Appointment setFinished(Appointment app) {
        System.out.println("usao u setFinished");
        app.setFinished(true);
        Appointment savedApp = appointmentRepository.save(app);
        return savedApp;
    }


}
