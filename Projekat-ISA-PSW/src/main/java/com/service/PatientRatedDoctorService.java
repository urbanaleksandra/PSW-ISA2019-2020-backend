package com.service;

import com.model.PatientRatedDoctor;
import com.repository.PatientRatedDoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientRatedDoctorService {

    @Autowired
    private PatientRatedDoctorRepository patientRatedDoctorRepository;

    public PatientRatedDoctor save(PatientRatedDoctor prd){
        return patientRatedDoctorRepository.save(prd);
    }

    public List<PatientRatedDoctor> findAll() { return patientRatedDoctorRepository.findAll(); }


}
