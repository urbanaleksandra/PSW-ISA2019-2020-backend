package com.repository;

import com.model.Appointment;
import com.model.RequestAppointment;
import com.model.RequestUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestAppointmentRepository extends JpaRepository<RequestAppointment, Long> {

    List<RequestAppointment> findByClinicId(Long id);
}
