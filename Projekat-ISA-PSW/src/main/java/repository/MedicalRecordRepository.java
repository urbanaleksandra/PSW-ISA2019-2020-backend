package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.MedicalRecord;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long>{


}
