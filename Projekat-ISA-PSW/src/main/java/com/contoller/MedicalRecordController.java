package com.contoller;



import com.model.Appointment;
import com.model.Doctor;
import com.model.Surgery;
import com.service.AppointmentService;
import com.service.DoctorService;
import com.service.SurgeryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class MedicalRecordController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private SurgeryService surgeryService;

    @Autowired
    private DoctorService doctorService;


    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/get-patient-appointments/{username}/{doctor}", method= RequestMethod.GET)
    private List<Appointment> getAppointments(@PathVariable("username") String username, @PathVariable("doctor") String doctor) throws ParseException {
        List<Appointment> apps = appointmentService.findAll();
        List<Appointment> ret = new ArrayList<>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String today = dateFormat.format(new Date());


        for (Appointment app:apps) {
            Date date = dateFormat.parse(app.getDate());
            long milis = date.getTime();
            String newDate = dateFormat.format(milis);
            if(app.getPatient().equals(username) && app.getDoctorUsername().equals(doctor) && newDate.equals(today) && app.getHospitalRoom() != null){
                ret.add(app);
            }

        }
            return ret;
    }

//    @CrossOrigin(origins = "http://localhost:4200")
//    @RequestMapping(value="/get-patient-surgeries/{username}/{doctor}", method= RequestMethod.GET)
//    private List<Surgery> getSurgeries(@PathVariable("username") String username, @PathVariable("doctor") String doctor) throws ParseException {
//        Doctor doc = doctorService.findByUsername(doctor);
//        List<Surgery> sur = surgeryService.findByDoctorId(doc.getId());
//        List<Surgery> ret = new ArrayList<>();
//        System.out.println(doctor);
//        System.out.println(username);
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String today = dateFormat.format(new Date());
//        System.out.println(today);
//        for(Surgery surgery:sur){
//            if(surgery.getPatient().equals(username)){
//                Date date = dateFormat.parse(surgery.getDate());
//                long milis = date.getTime();
//                String newDate = dateFormat.format(milis);
//                if(newDate.equals(today))
//                    ret.add(surgery);
//            }
//        }
//        return ret;
//    }
}
