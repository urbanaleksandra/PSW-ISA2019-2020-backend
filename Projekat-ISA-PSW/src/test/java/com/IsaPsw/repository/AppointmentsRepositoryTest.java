package com.IsaPsw.repository;

import com.IsaPsw.constants.AppointmentConstants;
import com.model.*;
import com.repository.AppointmentRepository;
import static org.junit.Assert.*;

import com.service.AppointmentService;
import org.junit.Test;
import org.junit.runner.RunWith;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class AppointmentsRepositoryTest {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void findById() {
        System.out.println("usao u test");
        Appointment app = appointmentRepository.findById(AppointmentConstants.DB_APPOINTMENTS_ID).get();
        assertEquals(AppointmentConstants.DB_APPOINTMENTS_ID, app.getId());
    }

    @Test
    public void save(){
        Clinic c = new Clinic();
         c.setName("test");
         c.setDescription("test");
         c.setLat(25);
         c.setLongitude(552);
         c.setProfit(0);
         c.setRating(4);
         c.setAddress("test");
        this.entityManager.persist(c);

        Diagnosis d = new Diagnosis();
        d.setName("upala pluca") ;
        d.setDescription("temperatura");
        this.entityManager.persist(d);

        Recipe r = new Recipe().builder()
                .authenticated(true).build();
        this.entityManager.persist(r);

        AppointmentType at = new AppointmentType(1L,"test");
        this.entityManager.persist(at);

        Appointment app = new Appointment(1L, null, null, null, r, d, at, "ana", "doctor",
                "2020-02-22T16:00", "test", "stomatoloski", 2L, true, "test");


        Appointment app1 = this.appointmentRepository.save(app);
        assertEquals(app1.getId(), this.entityManager.getId(app1));
    }
}
