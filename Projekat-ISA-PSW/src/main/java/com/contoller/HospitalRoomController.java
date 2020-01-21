package com.contoller;

import com.dto.HospitalRoomDTO;
import com.model.ClinicalCenterAdministrator;
import com.model.HospitalRoom;
import com.model.Patient;
import com.service.HospitalRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    @RequestMapping(value="/delete-room", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public  @ResponseBody ResponseEntity<HospitalRoomDTO> deleteRoom(@RequestBody HospitalRoomDTO room){

        System.out.println(room.getName());
        HospitalRoom hr=hospitalRoomService.findByName(room.getName());
        hospitalRoomService.delete(hr);
        HospitalRoomDTO hRoom = new HospitalRoomDTO(room.getName(),room.getRoom_number());


        return new ResponseEntity<>(hRoom, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/changeRoomInfo/{name}", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<HospitalRoomDTO> changeRoomInfo(@RequestBody HospitalRoomDTO room, @PathVariable String name){

        HospitalRoom room1 = hospitalRoomService.findByName(name);
        if(room1 != null){
            room1.setName(room.getName());
            room1.setRoom_number(room.getRoom_number());
            System.out.println(room.getRoom_number());
            hospitalRoomService.save(room1);
        }
        else{
            HospitalRoom room2=new HospitalRoom();
            room2.setName(room.getName());
            room2.setRoom_number(room.getRoom_number());
            hospitalRoomService.save(room2);
        }
        HospitalRoomDTO room3 = new HospitalRoomDTO();
        return new ResponseEntity<>(room3, HttpStatus.OK);

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/add-room", method= RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public void addRoom(@RequestBody HospitalRoomDTO hospitalRoom){
        System.out.println(hospitalRoom);
        HospitalRoom hr=new HospitalRoom(hospitalRoom);
        hospitalRoomService.save(hr);

    }



}
