package com.service;

import com.model.Doctor;
import com.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public Doctor findByUsername(String username) {
        return (Doctor) doctorRepository.findByUsername(username);
    }

    public Doctor findAll(){ return (Doctor) doctorRepository.findAll();}

    public Doctor save(Doctor patient) {
        return doctorRepository.save(patient);

    }
}
