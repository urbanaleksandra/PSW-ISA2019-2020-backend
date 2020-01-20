package com.service;

import com.model.HospitalRoom;
import com.model.RequestAppointment;
import com.repository.HospitalRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalRoomService {

    @Autowired
    private HospitalRoomRepository hospitalRoomRepository;

    public List<HospitalRoom> findAll() {
        return hospitalRoomRepository.findAll();

    }

    public HospitalRoom save(HospitalRoom room){
        return hospitalRoomRepository.save(room);
    }
}
