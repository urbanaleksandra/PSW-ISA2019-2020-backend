package com.service;

import com.model.Surgery;
import com.repository.SurgeryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurgeryService {

    @Autowired
    private SurgeryRepository surgeryRepository;

    public List<Surgery> findAll(){

        return surgeryRepository.findAll();
    }

    public List<Surgery> findByMedicalRecordId(Long id){
        return surgeryRepository.findByMedicalRecordId(id);
    }

    public List<Surgery> findByClinicId(Long id){
        return surgeryRepository.findByClinicId(id);
    }
}
