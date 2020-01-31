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
    public List<HospitalRoom> findByClinicId(Long id) {
        return hospitalRoomRepository.findByClinicId(id);

    }
    public HospitalRoom findById(Long id) { return hospitalRoomRepository.findById(id).get();}
    public HospitalRoom findByName(String name) {
        return hospitalRoomRepository.findByName(name);
    }

    public  void delete(HospitalRoom room) { hospitalRoomRepository.delete(room);}

    public HospitalRoom save(HospitalRoom room){
        return hospitalRoomRepository.save(room);
    }
}
