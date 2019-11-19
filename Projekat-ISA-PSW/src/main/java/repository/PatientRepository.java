package repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import model.Patient;
import java.lang.String;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long>{
	
	Page<Patient> findAll(Pageable pageable);
	
	List<Patient> findByFirstName(String firstname);
	
	List<Patient> findByUsername(String username);
}
