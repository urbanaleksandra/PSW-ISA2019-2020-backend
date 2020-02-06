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
	
	Page<Appointment> findAll(Pageable pageable);

	@Override
	List<Appointment> findAllById(Iterable<Long> longs);

	@Override
	Optional<Appointment> findById(Long aLong);

	@Override
	List<Appointment> findAll();

	@Override
	<S extends Appointment> List<S> saveAll(Iterable<S> entities);

	List<Appointment> findByFinished(Boolean finished);

	Appointment findByDate(String date);

	List<Appointment> findByHospitalRoomId(Long id);
}
