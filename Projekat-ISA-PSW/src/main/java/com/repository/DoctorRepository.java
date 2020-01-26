package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Doctor;
import java.lang.String;
import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long>{
	
	List<Doctor> findByFirstName(String firstname);
	
	Doctor findByUsername(String username);

	List<Doctor> findAll();
}
