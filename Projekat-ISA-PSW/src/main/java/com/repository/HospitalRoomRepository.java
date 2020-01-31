package com.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.HospitalRoom;

public interface HospitalRoomRepository extends JpaRepository<HospitalRoom, Long>{


    HospitalRoom findByName(String name);
    List<HospitalRoom> findByClinicId(Long id);
    Optional<HospitalRoom> findById(Long id);
}
