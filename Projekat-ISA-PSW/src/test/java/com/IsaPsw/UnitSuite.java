package com.IsaPsw;

import com.IsaPsw.controller.AppointmentControllerUnitTest;
import com.IsaPsw.controller.ClinicControllerUnitTest;
import com.IsaPsw.service.AppointmentServiceUnitTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ClinicControllerUnitTest.class,
        AppointmentControllerUnitTest.class,
        AppointmentServiceUnitTest.class,
})
public class UnitSuite {
}
