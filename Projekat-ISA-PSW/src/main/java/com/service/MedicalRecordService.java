package com.service;

import com.model.MedicalRecord;
import com.model.MedicalStaff;
import com.repository.MedicalStaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicalRecordService {

    @Autowired
    private MedicalRecordService medicalRecordService;

    public MedicalRecord save(MedicalRecord medicalRecord){
        return  medicalRecordService.save(medicalRecord);
    }
}
