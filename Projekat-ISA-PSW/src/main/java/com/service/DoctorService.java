package com.service;

import com.model.Doctor;
import com.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public Doctor findByUsername(String username) {
        return doctorRepository.findByUsername(username);
    }

    public List<Doctor> findAll(){ return doctorRepository.findAll();}

    public Doctor save(Doctor patient) {
        return doctorRepository.save(patient);

    }

    public  void delete(Doctor doc) { doctorRepository.delete(doc);}

    public List<Doctor> findByClinicId(Long id){ return doctorRepository.findByClinicId(id);}
    public Doctor findById(Long id) { return doctorRepository.findById(id).get();}

}
