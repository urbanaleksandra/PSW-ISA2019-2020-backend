package com.service;

import com.model.Nurse;
import com.repository.NurseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NurseService {

    @Autowired
    private NurseRepository doctorRepository;

    public Nurse findByUsername(String username) {
        return (Nurse) doctorRepository.findByUsername(username);
    }

    public Nurse save(Nurse patient) {
        return doctorRepository.save(patient);

    }
}
