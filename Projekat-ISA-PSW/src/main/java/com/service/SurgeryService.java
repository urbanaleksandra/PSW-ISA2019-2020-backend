package com.service;

import com.dto.ReservationHospitalRoomDTO;
import com.model.Doctor;
import com.model.HospitalRoom;
import com.model.Patient;
import com.model.Surgery;
import com.repository.SurgeryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SurgeryService {

    @Autowired
    private SurgeryRepository surgeryRepository;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private HospitalRoomService hospitalRoomService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private SurgeryService surgeryService;

    @Autowired
    private EmailService email;


    public List<Surgery> findAll(){

        return surgeryRepository.findAll();
    }

    public List<Surgery> findByMedicalRecordId(Long id){
        return surgeryRepository.findByMedicalRecordId(id);
    }

    public List<Surgery> findByClinicId(Long id){
        return surgeryRepository.findByClinicId(id);
    }

    public List<Surgery> findByHospitalId(Long id){
        return surgeryRepository.findByHospitalRoomId(id);
    }

    public Surgery save(Surgery surgery) { return surgeryRepository.save(surgery); }

    public Surgery findById(Long id) { return surgeryRepository.findById(id).get();}

    @Transactional(rollbackFor = {RuntimeException.class},readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED)
    public String AddRoomToSurgery(ReservationHospitalRoomDTO reservationHospitalRoomDTO){
        System.out.println(reservationHospitalRoomDTO);
        HospitalRoom hospitalRoom = this.hospitalRoomService.findById(reservationHospitalRoomDTO.getSurgery().getRoomID());
        Surgery surgery = this.surgeryService.findById(reservationHospitalRoomDTO.getSurgery().getId());
        surgery.setHospitalRoom(hospitalRoom);
        surgery.setDate(reservationHospitalRoomDTO.getSurgery().getDate());
        for (Long id:reservationHospitalRoomDTO.getDoctors()) {
            surgery.getDoctor().add(this.doctorService.findById(id));
        }
        Surgery s = this.surgeryService.save(surgery);
        for (Long id:reservationHospitalRoomDTO.getDoctors()) {
            try{
                s = addDoctorsToSurgery(id, s);
            }catch(Exception e){
                System.out.println("ne moze taj doktor");
                return "greska";
            }

        }
        Patient patient = patientService.findByUsername(s.getPatient());
        try{
            email.sendPatientNotificaition(s, patient);
        }catch (Exception e){
            System.out.println("email fail");
        }

        hospitalRoom.getSurgeries().add(s);
        HospitalRoom hospitalRoom1 = this.hospitalRoomService.save(hospitalRoom);
        return "ok";
    }

    @Transactional(rollbackFor = {RuntimeException.class},readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED)
    public Surgery addDoctorsToSurgery(Long id, Surgery s){
        Doctor doctor = this.doctorService.findById(id);
        doctor.getSurgeries().add(s);
        Doctor d = this.doctorService.save(doctor);
        try {
            email.sendDoctorNotificaition(s, doctor);
        }catch (Exception e){
            System.out.println("email fail");
        }

        return s;
    }
}
