package com.IsaPsw.service;

import static com.IsaPsw.constants.AppointmentConstants.*;

import com.dto.AppointmentDTO;
import com.dto.ClinicDTO;
import com.dto.DoctorDTO;
import com.model.*;
import static org.junit.Assert.*;
import com.repository.AppointmentRepository;
import com.service.AppointmentService;
import com.service.RequestAppointmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class AppointmentServiceTest {

    @Autowired
    public AppointmentService appointmentService;



    //positive
    @Test
    @Transactional
    public void sheduleTest(){
//        Recipe r = new Recipe().builder()
//                .authenticated(true).build();
//        Diagnosis d = new Diagnosis();
//        d.setName("upala pluca") ;
//        d.setDescription("temperatura");
//        Clinic c = new Clinic();
//        c.setName("test");
//        c.setDescription("test");
//        c.setLat(25);
//        c.setLongitude(552);
//        c.setProfit(0);
//        c.setRating(4);
//        c.setAddress("test");
//        AppointmentType at = new AppointmentType(1L,"test");
//        AppointmentDTO app = new AppointmentDTO(null, null, null, r, d, at, "ana", "doctor",
//                "2020-02-22T16:00", "test", "stomatoloski", 2L, true, "test");

        AppointmentDTO appDTO =new AppointmentDTO(1L,null, "2020-02-05T16:00", "cold", "test",
                2L,1L, "masa", "test", "test", null);


        Appointment appointment = appointmentService.schedule(appDTO);

        assertEquals(appointment.getPatient(), DB_APPOINTMENTS_PATIENT);
        assertEquals(appointment.getMedicalRecord().getId(), DB_APPOINTMENTS_MR);
    }

    @Test
    @Transactional
    public void  acceptAppointment(){
        AppointmentDTO appDTO =new AppointmentDTO(1L,null, "2020-02-05T16:00", "cold", "test",
                2L,1L, "masa", "doctor", "test", null);

        List<Appointment> appsBEFORE = appointmentService.findAll();
        Appointment appointment = appointmentService.acceptAppointment(appDTO);
        List<Appointment> appsAFTER = appointmentService.findAll();
        assertEquals(appointment.getPatient(), DB_APPOINTMENTS_PATIENT);
        assertEquals(appsAFTER.size(), appsBEFORE.size() + 1);
        assertNotEquals(appsAFTER.size(), appsBEFORE.size());

    }

    @Test //positive
    public void findAllTest() throws Exception {
        List<Appointment> apps = appointmentService.findAll();
    }

    @Test(expected = ValidationException.class) //negative
    public void findByIdFail() throws Exception {
        Appointment app = appointmentService.findOne(15L);
    }
}
