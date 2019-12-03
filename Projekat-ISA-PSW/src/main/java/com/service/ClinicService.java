package com.service;

import com.model.Clinic;
import com.repository.ClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClinicService {

    @Autowired
    private ClinicRepository clinicRepository;

    public Clinic save(Clinic clinic){
        return clinicRepository.save(clinic);
    }

    public List<Clinic> findAll(){
        return clinicRepository.findAll();
    }

    public Clinic findById(long id){
        return clinicRepository.findById(id).get();
    }

}
