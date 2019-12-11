package com.service;

import com.model.MedicalStaff;
import com.repository.MedicalStaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicalStaffService {

    @Autowired
    private MedicalStaffRepository medicalStaffRepository;

    public MedicalStaff findByUsername(String username){
        return medicalStaffRepository.findByUsername(username);
    }

    public MedicalStaff save(MedicalStaff medicalStaff){
        return medicalStaffRepository.save(medicalStaff);
    }
}
