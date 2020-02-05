package com.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Patient;
import java.lang.String;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long>{
	

	
	List<Patient> findByFirstName(String firstname);
	
	Patient findByUsername(String username);

	Patient findByUsernameIgnoreCase(String username);
}
