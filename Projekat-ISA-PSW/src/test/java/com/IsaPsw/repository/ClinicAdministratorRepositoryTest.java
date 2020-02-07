package com.IsaPsw.repository;

import com.model.ClinicAdministrator;
import com.repository.ClinicAdministratorRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static com.IsaPsw.constants.ClinicAdministratorConstants.DB_CLINIC_ADMIN_USERNAME;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class ClinicAdministratorRepositoryTest {

    @Autowired
    private ClinicAdministratorRepository clinicAdministratorRepository;

    @Test
    public void findByUsername() {

        ClinicAdministrator administrator = this.clinicAdministratorRepository.findByUsername(DB_CLINIC_ADMIN_USERNAME);

        assertEquals(DB_CLINIC_ADMIN_USERNAME, administrator.getId());
    }

}
