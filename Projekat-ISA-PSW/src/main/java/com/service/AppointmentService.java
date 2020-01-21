package com.service;

import com.model.Appointment;
import com.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

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
}
