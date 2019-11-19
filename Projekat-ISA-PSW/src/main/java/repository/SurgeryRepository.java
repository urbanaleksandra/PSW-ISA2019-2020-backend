package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Surgery;

public interface SurgeryRepository extends JpaRepository<Surgery, Long>{
	
}
