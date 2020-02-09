package com.service;

import com.dto.ClinicDTO;
import com.model.Clinic;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ClinicServiceInterface {

    public Clinic save(Clinic clinic);

    public List<Clinic> findAll();

    public Clinic findById(long id);

    public Clinic findByName(String name);

    public void delete(Clinic clinic);

    public Clinic changeInfo(ClinicDTO newClinic,String name);
}
