package com.service;

import com.dto.AppointmentDTO;
import com.model.Appointment;
import com.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface AppointmentServiceInterface {

    public Appointment save(Appointment appointment);

    public List<Appointment> findAll();

    public List<Appointment> findByFinished(Boolean finished);

    public Appointment findByDate(String date);

    public List<Appointment> findByHospitalRoomId(Long id);

    public Appointment findById(Long id);

    public Appointment setFinished(Appointment app);

    public Appointment schedule(AppointmentDTO appointment);
}
