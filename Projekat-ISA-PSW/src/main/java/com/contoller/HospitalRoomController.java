package com.contoller;

import com.dto.HospitalRoomDTO;
import com.model.*;
import com.service.ClinicAdministratorService;
import com.service.ClinicService;
import com.service.HospitalRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class HospitalRoomController {

    @Autowired
    private HospitalRoomService hospitalRoomService;

    @Autowired
    private ClinicService clinicService2;

    @Autowired
    private ClinicAdministratorService administratorService;

    @CrossOrigin
    @GetMapping("sale")
    public List<HospitalRoom> getHospitalRooms() {
        return hospitalRoomService.findAll();
    }

    @CrossOrigin
    @GetMapping("sale/{usernameAdmin}")
    public List<HospitalRoom> getHospitalRooms2(@PathVariable String usernameAdmin)
    {
        ClinicAdministrator clinicAdministrator=administratorService.findByUsername(usernameAdmin);
        List<HospitalRoom> hrooms=hospitalRoomService.findAll();
        List<HospitalRoom> ret=new ArrayList<>();
        for(int i=0;i<hrooms.size();i++) {
            if(hrooms.get(i).getClinic().getId().equals(clinicAdministrator.getClinic().getId())){
                ret.add(hrooms.get(i));
            }
        }
        return ret;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/delete-room", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public  @ResponseBody ResponseEntity<HospitalRoomDTO> deleteRoom(@RequestBody HospitalRoomDTO room){

        System.out.println(room.getName());
        HospitalRoom hr=hospitalRoomService.findByName(room.getName());
        Clinic clinic=clinicService2.findById(hr.getClinic().getId());
        System.out.println(clinic.getId());
        clinic.getHospitalRooms().remove(hr);
        System.out.println(clinic.getHospitalRooms());
        clinicService2.save(clinic);
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
    @RequestMapping(value="/add-room/{username}", method= RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public void addRoom(@RequestBody HospitalRoomDTO hospitalRoom,@PathVariable String username){
        // username je username admina koji je dodao kliniku. Ovo cemo iskoristiti da bismo dodijelili sobu klinici
        System.out.println(username);
        HospitalRoom hr=new HospitalRoom(hospitalRoom);
        hospitalRoomService.save(hr);
        ClinicAdministrator clinicAdministrator=administratorService.findByUsername(username);

        if(clinicAdministrator!=null) {
            Clinic clinic = clinicService2.findById(clinicAdministrator.getClinic().getId());
            if (clinic!=null) {
                clinic.getHospitalRooms().add(hr);
                System.out.println(clinic);
                hr.setClinic(clinic);
                System.out.println(hr.getClinic().getId());
                hospitalRoomService.save(hr);
                clinicService2.save(clinic);
                hospitalRoomService.save(hr);
            }
            }



    }



}
