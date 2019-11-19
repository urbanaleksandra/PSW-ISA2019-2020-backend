package repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long>{
	
	Page<Appointment> findAll(Pageable pageable);

}
