package com.service;

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

    public void delete(RequestAppointment appointment){
        requestAppointmentRepositoryt.delete(appointment);
    }

    public List<RequestAppointment> findAll(){
        return requestAppointmentRepositoryt.findAll();
    }
}



