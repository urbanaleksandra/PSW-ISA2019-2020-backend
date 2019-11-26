package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Nurse;

public interface NurseRepository extends JpaRepository<Nurse, Long>{

}
