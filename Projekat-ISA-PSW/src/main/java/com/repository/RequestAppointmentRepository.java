package com.repository;

import com.model.RequestAppointment;
import com.model.RequestUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestAppointmentRepository extends JpaRepository<RequestAppointment, Long> {
}
