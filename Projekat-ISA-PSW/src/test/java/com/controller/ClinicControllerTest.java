package com.controller;


import static org.junit.Assert.assertEquals;
import static com.constants.ClinicConstants.*;

import com.dto.ClinicDTO;
import com.model.Clinic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import java.nio.charset.Charset;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class ClinicControllerTest {
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

    @Test
    public void getClinic() throws Exception{
        ResponseEntity<ClinicDTO[]> responseEntity =
                restTemplate.exchange(URL_PREFIX + "api/get-clinics", HttpMethod.GET, httpEntity, ClinicDTO[].class);

        ClinicDTO[] clinics = responseEntity.getBody();
        for (ClinicDTO C:clinics) {

            System.out.println(C);
        }

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(DB_CLINIC_COUNT, clinics.length);
        assertEquals(DB_CLINIC_ID, clinics[3].getId());
        assertEquals(DB_CLINIC_NAME, clinics[3].getName());
        assertEquals(DB_CLINIC_ADDRESS, clinics[3].getAddress());
        assertEquals(DB_CLINIC_DESCRIPTION, clinics[3].getDescription());
        assertEquals(DB_CLINIC_PROFIT, clinics[3].getProfit());
        assertEquals(DB_CLINIC_RATING, clinics[3].getRating());
        System.out.println("prosao sve aserte");
    }
}
