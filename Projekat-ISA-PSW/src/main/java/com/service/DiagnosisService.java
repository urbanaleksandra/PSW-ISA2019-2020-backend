package com.service;

import com.model.Diagnosis;
import com.repository.DiagnosisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiagnosisService {

    @Autowired
    private DiagnosisRepository diagnosisRepository;

    public Diagnosis save(Diagnosis diagnosis){
        return diagnosisRepository.save(diagnosis);
    }

    public List<Diagnosis> findAll(){ return diagnosisRepository.findAll(); }

    public Diagnosis findById(Long id) { return diagnosisRepository.findById(id).get();}

}
