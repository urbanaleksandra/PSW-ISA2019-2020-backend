package com.IsaPsw;

import com.IsaPsw.e2e.ScheduleAppointmentE2ETest;
import com.IsaPsw.service.AppointmentServiceUnitTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ScheduleAppointmentE2ETest.class
})
public class SeleniumSuite {
}
