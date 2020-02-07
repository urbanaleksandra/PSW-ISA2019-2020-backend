package com.service;


import com.model.ClinicAdministrator;
import com.repository.ClinicAdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicAdministratorService {

    @Autowired
    private ClinicAdministratorRepository clinicAdministratorRepository;


    public ClinicAdministrator findByUsername(String username) {
        return  clinicAdministratorRepository.findByUsername(username);
    }

    public ClinicAdministrator save(ClinicAdministrator clinicAdministrator){
        return clinicAdministratorRepository.save(clinicAdministrator);
    }

    public List<ClinicAdministrator> findByClinicId(Long id){
        return clinicAdministratorRepository.findByClinicId(id);
    }
}
