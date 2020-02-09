package com.IsaPsw.service;
import static com.IsaPsw.constants.AppointmentConstants.*;
import com.dto.AppointmentDTO;
import com.model.*;
import com.repository.AppointmentRepository;
import com.repository.PatientRepository;
import com.service.AppointmentService;
import com.service.MedicalRecordService;
import com.service.PatientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;


import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.mockito.BDDMockito.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
public class AppointmentServiceUnitTest {

    @Mock
    public AppointmentRepository appointmentRepositoryMock;

    @InjectMocks
    public AppointmentService appointmentService;

    @Mock
    public Appointment appointmentMock;

    @Mock
    private PatientRepository patientRepositoryMock;

    @Mock
    private MedicalRecordService medicalRecordService;

    @Mock
    public PatientService patientService;

    @Test
    public void testFindAll(){


        Appointment app1 = new Appointment(1L,null, null, null, null, null, null, "test", "test", "test", "test", "test",
        2L, false, "test");
        app1.setId(1L);
        app1.setPrice(2000);
        when(appointmentRepositoryMock.findAll()).thenReturn(Arrays.asList(app1));
        List<Appointment> apps = appointmentService.findAll();
        assertEquals(apps.size(), 1);

    }

    @Test
    public void testFindOne() {
        Appointment app1 = new Appointment(1L, null, null, null, null, null, null, "test", "test", "test", "test", "test",
                2L, false, "test");

        given(this.appointmentRepositoryMock.findById(DB_APPOINTMENTS_ID)).willReturn(Optional.of(app1));
        Appointment dbAppointment = appointmentService.findById(DB_APPOINTMENTS_ID);
//        System.out.println(dbAppointment);
//        System.out.println(appointmentMock.getHospitalRoom());
        assertEquals(Optional.of(1L).get(), dbAppointment.getId());
        verify(appointmentRepositoryMock, times(1)).findById(DB_APPOINTMENTS_ID);
        verifyNoMoreInteractions(appointmentRepositoryMock);
    }

    @Test
    public void sheduleTest(){
        AppointmentDTO appDTO =new AppointmentDTO(1L,null, "2020-02-05T16:00", "cold", "test",
                2L,1L, DB_APPOINTMENTS_PATIENT, "doctor", "test", null);

        HospitalRoom hr = new HospitalRoom(1l, "Operaciona soba", 1);

        Appointment app1 = new Appointment(1L, null, hr, null,
                null, null, null, DB_APPOINTMENTS_PATIENT, "doctor", "2020-02-05T16:00", "cold", "test",
                2L, false, "test");

        Patient patient = new Patient();
        patient.setId(1L);
        patient.setUsername("masa");
        given(this.patientService.findByUsername(DB_APPOINTMENTS_PATIENT)).willReturn(patient);
        MedicalRecord mr= new MedicalRecord();
        mr.setId(1L);
        mr.setPatient(patient);
        given(this.medicalRecordService.findByPatientId(patient.getId())).willReturn(mr);
        given(this.appointmentRepositoryMock.findById(1L)).willReturn(Optional.of(app1));
        Appointment appointment = appointmentService.schedule(appDTO);

        assertEquals(appointment.getMedicalRecord().getId(), mr.getId());

    }


}
