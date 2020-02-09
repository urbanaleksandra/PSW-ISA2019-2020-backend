package com.IsaPsw.controller;

import static com.IsaPsw.constants.AppointmentConstants.*;

import com.IsaPsw.TestUtil;
import com.dto.AppointmentDTO;
import com.model.Appointment;
import com.model.Clinic;
import com.model.MedicalRecord;
import com.model.Patient;
import com.service.HospitalRoomService;
import com.service.MedicalRecordService;
import com.service.MedicalStaffService;
import com.service.PatientService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

import static com.IsaPsw.constants.ClinicConstants.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class AppointmentControllerUnitTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private TestRestTemplate restTemplate;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype());

    @Mock
    private HospitalRoomService hospitalRoomServiceMock;

    @Mock
    private MedicalStaffService medicalStaffServiceMock;

    @Mock
    private MedicalRecordService medicalRecordServiceMock;

    @Mock
    private PatientService patientServiceMock;
    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetAllAppointments() throws Exception {
        mockMvc.perform(get( "/appointments")).andExpect(status().isOk())
                .andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(DB_APPOINTMENTS_COUNT)))
                .andExpect(jsonPath("$.[*].id").value(hasItem(DB_APPOINTMENTS_ID.intValue())))
                .andExpect(jsonPath("$.[*].date").value(hasItem(DB_APPOINTMENTS_DATE)))
                .andExpect(jsonPath("$.[*].description").value(hasItem(DB_APPOINTMENTS_DESCRIPTION)))
                .andExpect(jsonPath("$.[*].patient").value(hasItem(DB_APPOINTMENTS_PATIENT)));
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testAddRoomToAppointment() throws Exception {
        AppointmentDTO appointment = new AppointmentDTO();
        appointment.setId(DB_APPOINTMENTS_ID);
        appointment.setDate(DB_APPOINTMENTS_DATE);
        appointment.setPatient(DB_APPOINTMENTS_PATIENT);
        appointment.setDescription(DB_APPOINTMENTS_DESCRIPTION);
        appointment.setInfo(DB_APPOINTMENTS_DESCRIPTION);
        appointment.setRoomID(1L);
        appointment.setType("Kardioloski");
        appointment.setDoctorUsername("doctor");

        Patient patient = new Patient();
        patient.setUsername("masa");
        patient.setId(1L);
        given(this.patientServiceMock.findByUsername(DB_APPOINTMENTS_PATIENT)).willReturn(patient);

        MedicalRecord mr = new MedicalRecord();
        mr.setId(1L);
        mr.setPatient(patient);
        given(this.medicalRecordServiceMock.findByPatientId(patient.getId())).willReturn(mr);


        String json = TestUtil.json(appointment);
        this.mockMvc.perform(post("/api/add-room-app").contentType(contentType)
                .content(json))
                .andExpect(jsonPath("$.[*].id").value(hasItem(appointment.getId().intValue())))
                .andExpect(status().isOk());
    }
}
