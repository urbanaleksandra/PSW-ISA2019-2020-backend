package com.IsaPsw.controller;


import static org.junit.Assert.assertEquals;

import com.IsaPsw.constants.ClinicConstants;
import com.dto.ClinicDTO;
import com.dto.DoctorDTO;
import com.model.Clinic;
import org.junit.Assert;
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
        Assert.assertEquals(ClinicConstants.DB_CLINIC_COUNT, clinics.length);
        Assert.assertEquals(ClinicConstants.DB_CLINIC_ID, clinics[3].getId());
        Assert.assertEquals(ClinicConstants.DB_CLINIC_NAME, clinics[3].getName());
        Assert.assertEquals(ClinicConstants.DB_CLINIC_ADDRESS, clinics[3].getAddress());
        Assert.assertEquals(ClinicConstants.DB_CLINIC_DESCRIPTION, clinics[3].getDescription());
        Assert.assertEquals(ClinicConstants.DB_CLINIC_PROFIT, clinics[3].getProfit());
        Assert.assertEquals(ClinicConstants.DB_CLINIC_RATING, clinics[3].getRating());
        System.out.println("prosao sve aserte");
    }

    @Test //positive
    public void getSearchClinic() throws Exception{
        ResponseEntity<ClinicDTO[]> responseEntity =
                restTemplate.exchange(URL_PREFIX + "api/get-search-clinics/"+ClinicConstants.DB_DATE + "/" + ClinicConstants.DB_TYPE, HttpMethod.GET, httpEntity, ClinicDTO[].class);

        //checks if returns list
        ClinicDTO[] appts = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assert appts != null;
    }

    @Test //positive
    public void getSearchDoctors() throws Exception{
        ResponseEntity<DoctorDTO[]> responseEntity =
                restTemplate.exchange(URL_PREFIX + "api/get-search-doctors/"+ClinicConstants.DB_DATE + "/" + ClinicConstants.DB_CLINIC_NAME + "/" + ClinicConstants.DB_TYPE, HttpMethod.GET, httpEntity, DoctorDTO[].class);

        //checks if returns list
        DoctorDTO[] appts = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assert appts != null;
    }

}
