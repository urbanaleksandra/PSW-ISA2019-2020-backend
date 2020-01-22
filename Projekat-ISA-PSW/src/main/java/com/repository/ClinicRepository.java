package com.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Clinic;

public interface ClinicRepository extends JpaRepository<Clinic, Long>{

	List<Clinic> findAllByName(String name);


    Clinic findByName(String name);
}
