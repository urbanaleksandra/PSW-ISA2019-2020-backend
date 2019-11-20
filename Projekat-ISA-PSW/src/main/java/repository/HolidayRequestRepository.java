package repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import model.HolidayRequest;

public interface HolidayRequestRepository extends JpaRepository<HolidayRequest, Long>{


	
}
