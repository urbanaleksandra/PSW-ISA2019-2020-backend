package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.MedicalRecord;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long>{


}
