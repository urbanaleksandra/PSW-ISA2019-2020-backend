package com.contoller;

import com.dto.HospitalRoomDTO;
import com.model.ClinicalCenterAdministrator;
import com.model.HospitalRoom;
import com.model.Patient;
import com.service.HospitalRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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


    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/add-room", method= RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public void addRoom(@RequestBody HospitalRoomDTO hospitalRoom){
        System.out.println(hospitalRoom);
        HospitalRoom hr=new HospitalRoom(hospitalRoom);
        hospitalRoomService.save(hr);

    }
}
