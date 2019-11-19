package repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import model.ClinicalCenterAdministrator;
import java.lang.String;

public interface ClinicalCenterAdministratorRepository extends JpaRepository<ClinicalCenterAdministrator, Long>{

		List<ClinicalCenterAdministrator> findAll();
		
		List<ClinicalCenterAdministrator> findByUsername(String username);
}
