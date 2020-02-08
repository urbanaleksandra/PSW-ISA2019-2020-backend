package com.service;

import com.model.Appointment;
import com.model.RequestAppointment;
import com.repository.RequestAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestAppointmentService {

    @Autowired
    private RequestAppointmentRepository requestAppointmentRepositoryt;

    public RequestAppointment save(RequestAppointment appointment){
        return requestAppointmentRepositoryt.save(appointment);
    }

    public List<RequestAppointment> findByClinicId(Long id){
        return requestAppointmentRepositoryt.findByClinicId(id);
    }
    public RequestAppointment findById(Long id){ return requestAppointmentRepositoryt.findById(id).get(); }

    public void delete(RequestAppointment appointment){
        System.out.println("usao");
        requestAppointmentRepositoryt.delete(appointment);
        System.out.println("obrisao");
    }

    public List<RequestAppointment> findAll(){
        return requestAppointmentRepositoryt.findAll();
    }
}



