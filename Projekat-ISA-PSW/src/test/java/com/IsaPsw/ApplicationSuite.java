package com.IsaPsw;

import com.IsaPsw.controller.ClinicalCenterAdministratorContollerTest;
import com.IsaPsw.controller.ClinicControllerTest;
import com.IsaPsw.repository.AppointmentsRepositoryTest;
import com.IsaPsw.repository.ClinicAdministratorRepositoryTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ClinicalCenterAdministratorContollerTest.class,
        ClinicControllerTest.class
})
public class ApplicationSuite {
}
