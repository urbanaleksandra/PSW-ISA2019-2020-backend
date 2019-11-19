package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.MedicalStaff;

public interface MedicalStaffRepository extends JpaRepository<MedicalStaff, Long>{
	
	List<MedicalStaff> findByUsername(String username);
	
	List<MedicalStaff> findByFirstName(String firstName);
	
}
