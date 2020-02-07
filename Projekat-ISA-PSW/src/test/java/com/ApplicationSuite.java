package com;

import com.controller.ClinicControllerTest;
import com.controller.ClinicalCenterAdministratorContollerTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ClinicalCenterAdministratorContollerTest.class,
        ClinicControllerTest.class
})
public class ApplicationSuite {
}
