package com.contoller;

import com.model.HospitalRoom;
import com.model.Patient;
import com.service.HospitalRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class HospitalRoomController {

    @Autowired
    private HospitalRoomService hospitalRoomService;

    @CrossOrigin
    @GetMapping("sale")
    public List<HospitalRoom> getHospitalRooms() {
        return hospitalRoomService.findAll();
    }

}
