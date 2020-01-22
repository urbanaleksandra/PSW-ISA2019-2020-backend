package com.service;

import com.model.Appointment;
import com.model.AppointmentType;
import com.repository.AppointmentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentTypeService {

    @Autowired
    private AppointmentTypeRepository repository;

    public AppointmentType save(AppointmentType appointmentType){
        return repository.save(appointmentType);
    }

    public List<AppointmentType> findAll(){
        return repository.findAll();
    }

    public AppointmentType findByName(String name) {
        return repository.findByName(name);
    }
    public  void delete(AppointmentType type) { repository.delete(type);}
}
