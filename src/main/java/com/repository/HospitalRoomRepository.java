package com.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.HospitalRoom;
import com.model.Surgery;

public interface HospitalRoomRepository extends JpaRepository<HospitalRoom, Long>{


	
}
