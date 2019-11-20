package repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import model.ClinicAdministrator;

public interface ClinicAdministratorRepository extends JpaRepository<ClinicAdministrator, Long>{
	
	List<ClinicAdministrator> findByUsername(String username);
	
	Page<ClinicAdministrator> findAll(Pageable pageable);
}
