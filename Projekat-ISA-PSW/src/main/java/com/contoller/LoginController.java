package com.contoller;

import com.model.*;
import com.repository.ClinicalCenterAdministratorRepository;
import com.security.JwtAuthenticationRequest;
import com.security.TokenUtils;
import com.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class LoginController {

    @Autowired
    private ClinicalCenterAdministratorService clinicalCenterAdministratorService;

    @Autowired
    private RequestService requestService;

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PatientService patientService;

    @Autowired
    private ClinicAdministratorService clinicAdministratorService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private NurseService nurseService;

    @Autowired
    private ClinicalCenterAdministratorRepository clinicalCenterAdministratorRepository;

    @Autowired
    private MedicalStaffService medicalStaffService;

    @CrossOrigin(origins = "http://localhost:4200")
    //@PostMapping(value = "/findByUsernameAndPassword")
    @RequestMapping(value="/login", method= RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public User postCCAByUsernameAndPassword(@RequestBody User user) {

        System.out.println("usao");
        System.out.println(user);
        ClinicalCenterAdministrator cca;
        try{
            cca = clinicalCenterAdministratorService.findByUsername(user.getUsername());
        }catch(Exception e){
            cca = null;
        }

        String ret = "none";
        Patient pa = null;
        ClinicAdministrator ca = null;
        Doctor doc = null;
        Nurse nur = null;
        MedicalStaff ms = null;
//		ClinicalCenterAdministrator cca = (ClinicalCenterAdministrator) authentication.getPrincipal();
        if(cca == null) {

            try{
                pa = patientService.findByUsername(user.getUsername());
            }catch(Exception e){
                pa = null;
            }


            if(pa == null){

                try{
                    ca = clinicAdministratorService.findByUsername(user.getUsername());
                }catch(Exception e){
                    ca = null;
                }


                if(ca == null) {

                    try{
                        ms = medicalStaffService.findByUsername(user.getUsername());
                    }catch(Exception e){
                        ms = null;
                    }



                    if(ms == null){
                        {user.setRole("NONE");
                        return  user;}
                    }
                    else {
                        if (!user.getPassword().equals(ms.getPassword())) {
                            user.setRole("NONE");
                            return user;
                        }
                        if (ms.getRole().equals("nurse"))
                            user.setRole("NURSE");
                        else if (ms.getRole().equals("doctor"))
                            user.setRole("DOC");
                    }

                }
                else{
                    if(!user.getPassword().equals(ca.getPassword()))
                    {user.setRole("NONE");
                        return  user;}
                    user.setRole("CA");
                }
            }
            else{
                if(!user.getPassword().equals(pa.getPassword()))
                {
                    user.setRole("NONE");
                    return  user;
                }
                else if(user.getPassword().equals(pa.getPassword()) && pa.isEnabled()){
                    user.setRole("PA");
                }

            }
        }
        else{
            if(!user.getPassword().equals(cca.getPassword()))
            {user.setRole("NONE");
                return  user;}
            user.setRole("CCA");
        }

        return user;

    }


}
