package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.ClinicalCenter;

public interface ClinicalCenterRepository extends JpaRepository<ClinicalCenter, Long>{

	List<ClinicalCenter> findByName(String name);
	
	
}
