package com.IsaPsw.service;
import static com.IsaPsw.constants.AppointmentConstants.*;
import com.dto.AppointmentDTO;
import com.model.*;
import com.repository.AppointmentRepository;
import com.service.AppointmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
public class AppointmentServiceUnitTest {

    @Mock
    private AppointmentRepository appointmentRepositoryMock;

    @InjectMocks
    private AppointmentService appointmentService;

    @Mock
    public Appointment appointmentMock;

    @Test
    public void testFindAll(){


        Appointment app1 = new Appointment(null, null, null, null, null, null, "test", "test", "test", "test", "test",
        2L, false, "test");
        app1.setId(1L);
        app1.setPrice(2000);
        when(appointmentRepositoryMock.findAll()).thenReturn(Arrays.asList(app1));
        List<Appointment> apps = appointmentService.findAll();
        assertEquals(apps.size(), 1);

    }

    @Test
    public void testFindOne() {
        Appointment app1 = new Appointment(null, null, null, null, null, null, "test", "test", "test", "test", "test",
                2L, false, "test");
        when(appointmentRepositoryMock.findById(DB_APPOINTMENTS_ID).get()).thenReturn(appointmentMock);
        Appointment dbAppointment = appointmentService.findOne(DB_APPOINTMENTS_ID);
        assertEquals(appointmentMock, dbAppointment);
        verify(appointmentRepositoryMock, times(1)).findById(DB_APPOINTMENTS_ID);
        verifyNoMoreInteractions(appointmentRepositoryMock);
    }


}
