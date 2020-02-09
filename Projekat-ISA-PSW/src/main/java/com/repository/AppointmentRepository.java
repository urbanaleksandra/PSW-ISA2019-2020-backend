package com.repository;

import com.model.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Appointment;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long>{

	Optional<Appointment> findById(Long aLong);

	List<Appointment> findAll();

	List<Appointment> findByFinished(Boolean finished);

	Appointment findByDate(String date);

	List<Appointment> findByHospitalRoomId(Long id);

}
