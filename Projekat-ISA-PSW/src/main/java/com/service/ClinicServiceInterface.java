package com.service;

import com.model.Clinic;

import java.util.List;

public interface ClinicServiceInterface {

    public Clinic save(Clinic clinic);

    public List<Clinic> findAll();

    public Clinic findById(long id);

    public Clinic findByName(String name);

    public void delete(Clinic clinic);
}
