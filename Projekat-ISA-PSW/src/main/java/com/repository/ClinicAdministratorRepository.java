package com.repository;

import java.util.List;
import java.util.Optional;

import com.model.Clinic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.model.ClinicAdministrator;

public interface ClinicAdministratorRepository extends JpaRepository<ClinicAdministrator, Long>{
	
	ClinicAdministrator findByUsername(String username);
	
	Page<ClinicAdministrator> findAll(Pageable pageable);

	List<ClinicAdministrator> findByClinicId(Long id);

}
