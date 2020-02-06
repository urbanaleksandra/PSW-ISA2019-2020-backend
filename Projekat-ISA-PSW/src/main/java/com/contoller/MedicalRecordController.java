package com.contoller;



import com.dto.MedicalRecordDTO;
import com.model.*;
import com.service.*;
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

    @Autowired
    private PatientService patientService;

    @Autowired
    private MedicalRecordService medicalRecordService;


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
            try {
                if (app.getPatient().equals(username) && app.getDoctorUsername().equals(doctor)
                        && newDate.equals(today) && app.getHospitalRoom() != null && app.isFinished() == false) {
                    ret.add(app);
                }
            }catch (Exception e){
                System.out.println("neko polje nije uneto");
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

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/api/get-medical-record-info/{username}", method= RequestMethod.GET)
    private MedicalRecordDTO getMedicalRecordInfo(@PathVariable String username){
        MedicalRecordDTO medicalRecordDTO = new MedicalRecordDTO();
        Patient patient = patientService.findByUsername(username);
        System.out.println(username);
        System.out.println(patient.getId());
        MedicalRecord medicalRecord = medicalRecordService.findByPatientId(patient.getId());
        medicalRecordDTO.setBloodType(medicalRecord.getBloodType());
        medicalRecordDTO.setDiopter(medicalRecord.getDiopter());
        medicalRecordDTO.setHeight(medicalRecord.getHeight());
        medicalRecordDTO.setWeight(medicalRecord.getWeight());
        medicalRecordDTO.setPatientUsername(username);
        System.out.println(medicalRecordDTO);
        return medicalRecordDTO;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/api/set-medical-record-info", method= RequestMethod.POST)
    private MedicalRecordDTO setMedicalRecordInfo(@RequestBody MedicalRecordDTO medicalRecordDTO){
        Patient patient = patientService.findByUsername(medicalRecordDTO.getPatientUsername());
        MedicalRecord medicalRecord = medicalRecordService.findByPatientId(patient.getId());
        medicalRecord.setBloodType(medicalRecordDTO.getBloodType());
        medicalRecord.setDiopter(medicalRecordDTO.getDiopter());
        medicalRecord.setHeight(medicalRecordDTO.getHeight());
        medicalRecord.setWeight(medicalRecordDTO.getWeight());
        medicalRecordDTO.setId(medicalRecord.getId());

        MedicalRecord md = medicalRecordService.save(medicalRecord);
        return medicalRecordDTO;
    }
}
