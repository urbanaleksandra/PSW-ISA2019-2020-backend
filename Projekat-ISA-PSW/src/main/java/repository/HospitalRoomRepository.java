package repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import model.HospitalRoom;
import model.Surgery;

public interface HospitalRoomRepository extends JpaRepository<HospitalRoom, Long>{

	List<HospitalRoom> findBySurgeries(Set<Surgery> surgeries);
	
}
