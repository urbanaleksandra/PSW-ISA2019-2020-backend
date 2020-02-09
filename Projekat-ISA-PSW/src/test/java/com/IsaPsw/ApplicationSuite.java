package com.IsaPsw;

import com.IsaPsw.controller.AppointmentsControllerTest;
import com.IsaPsw.controller.ClinicalCenterAdministratorContollerTest;
import com.IsaPsw.controller.ClinicControllerTest;
import com.IsaPsw.e2e.ScheduleAppointmentE2ETest;
import com.IsaPsw.repository.AppointmentsRepositoryTest;
import com.IsaPsw.repository.ClinicAdministratorRepositoryTest;

import com.IsaPsw.service.AppointmentServiceUnitTest;
import com.service.AppointmentService;
import com.IsaPsw.service.AppointmentServiceTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ClinicalCenterAdministratorContollerTest.class,
        ClinicControllerTest.class,
        AppointmentsControllerTest.class,
        AppointmentServiceTest.class,
        ScheduleAppointmentE2ETest.class,
})
public class ApplicationSuite {
}
