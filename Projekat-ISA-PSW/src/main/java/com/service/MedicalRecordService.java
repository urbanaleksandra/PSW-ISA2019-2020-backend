package com.service;

import com.model.MedicalRecord;
import com.repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicalRecordService {

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    public MedicalRecord save(MedicalRecord medicalRecord){
        return  medicalRecordRepository.save(medicalRecord);
    }

    public MedicalRecord findByPatientId(Long id) {
        return  medicalRecordRepository.findByPatientId(id);
    }

}
