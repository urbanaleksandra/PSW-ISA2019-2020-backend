package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Doctor;
import java.lang.String;
import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long>{
	
	List<Doctor> findByFirstName(String firstname);
	
	List<Doctor> findByUsername(String username);
}
