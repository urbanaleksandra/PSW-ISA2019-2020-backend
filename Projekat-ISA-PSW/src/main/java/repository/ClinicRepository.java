package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Clinic;

public interface ClinicRepository extends JpaRepository<Clinic, Long>{

	List<Clinic> findAllByName(String name);
	
	
}
