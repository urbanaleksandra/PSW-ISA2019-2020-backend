package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Surgery;

public interface SurgeryRepository extends JpaRepository<Surgery, Long>{
	
}
