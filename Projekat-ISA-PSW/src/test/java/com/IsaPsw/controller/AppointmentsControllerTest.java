package com.IsaPsw.controller;

import com.dto.AppointmentDTO;
import com.dto.ClinicDTO;
import com.dto.DoctorDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.model.Appointment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.MultiValueMap;

import java.nio.charset.Charset;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class AppointmentsControllerTest {

    private static final String URL_PREFIX = "/";
    private MediaType contentType = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private Environment env;
    private String accessToken;
    HttpHeaders headers = new HttpHeaders();
    HttpEntity<Object> httpEntity;



//    @Test
//    public void getAppointments2() {
//        ResponseEntity<AppointmentDTO[]> responseEntity =
//                restTemplate.exchange(URL_PREFIX + "/appointments", HttpMethod.GET, httpEntity, AppointmentDTO[].class);
//
//        //checks if returns list
//        AppointmentDTO[] appts = responseEntity.getBody();
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assert appts != null;
//    }

    //positive
     @Test
    public void addApp() throws Exception
    {
        AppointmentDTO appointment = new AppointmentDTO();
        appointment.setId(1L);
        appointment.setDate("2020-04-02T10:00");
        appointment.setDescription("opis1");
        appointment.setDuration(2);
        appointment.setPatient("masa");
        appointment.setType("tip1");
        appointment.setDoctorUsername("Ivana");
        appointment.setRoomID(1L);
        appointment.setPrice(2000);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        HttpHeaders header = new HttpHeaders();

        header.add("Content-Type", "application/json");

        httpEntity = new HttpEntity<>(ow.writeValueAsString(appointment), header);

        ResponseEntity<AppointmentDTO> responseEntity =
                restTemplate.exchange(URL_PREFIX + "/api/add-room-app", HttpMethod.POST, httpEntity, AppointmentDTO.class);



        AppointmentDTO appointmentDTO = responseEntity.getBody();
        //check if it has HTTP 200 OK STATUS
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        // check if date is good
        assertEquals(appointmentDTO.getDate(),appointment.getDate());
        //check if chosen room is set
        assertEquals(appointmentDTO.getHospitalRoom().getId(),appointment.getRoomID());

    }

    //negative
    @Test
    public void addApp2() throws Exception
    {
        AppointmentDTO appointment = new AppointmentDTO();
        appointment.setId(1L);
        appointment.setDate("2020-04-02T10:00");
        appointment.setDescription("opis1");
        appointment.setDuration(2);
        appointment.setPatient("masa");
        appointment.setType("tip1");
        appointment.setDoctorUsername("Ivana");
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        HttpHeaders header = new HttpHeaders();

        header.add("Content-Type", "application/json");

        httpEntity = new HttpEntity<>(ow.writeValueAsString(appointment), header);

        ResponseEntity<AppointmentDTO> responseEntity =
                restTemplate.exchange(URL_PREFIX + "/api/add-room-app", HttpMethod.POST, httpEntity, AppointmentDTO.class);

        AppointmentDTO appointmentDTO = responseEntity.getBody();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        //  assertEquals(ClinicConstants.DB_ID, appt.getClinic().getId());
    }


    @Test
    public void scheduleApp() throws Exception
    {
        AppointmentDTO appointment = new AppointmentDTO();
        appointment.setId(1L);
        appointment.setDate("2020-04-02T10:00");
        appointment.setDescription("opis1");
        appointment.setDuration(2);
        appointment.setPatient("masa");
        appointment.setType("tip1");
        appointment.setDoctorUsername("Ivana");
        appointment.setPrice(2000);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        HttpHeaders header = new HttpHeaders();

        header.add("Content-Type", "application/json");

        httpEntity = new HttpEntity<>(ow.writeValueAsString(appointment), header);

        ResponseEntity<AppointmentDTO> responseEntity =
                restTemplate.exchange(URL_PREFIX + "/scheduleApp", HttpMethod.POST, httpEntity, AppointmentDTO.class);


        AppointmentDTO appointmentDTO = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }

}
