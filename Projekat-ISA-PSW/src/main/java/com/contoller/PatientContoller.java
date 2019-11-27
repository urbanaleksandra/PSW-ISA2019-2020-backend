package com.contoller;

import com.model.Patient;
import com.model.RequestUser;
import com.service.ClinicalCenterAdministratorService;
import com.service.PatientService;
import com.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class PatientContoller {
	
	@Autowired
	private PatientService patientService;

	@Autowired
	private ClinicalCenterAdministratorService clinicalCenterAdministratorService;

	@Autowired
	private RequestService requestService;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value="/register", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<RequestUser> registrujPacijenta(@RequestBody Patient patientNovi){
		
		//Patient patient = new Patient(patientNovi.getUsername(), patientNovi.getPassword(), patientNovi.getFirstName(), patientNovi.getLastName(), patientNovi.getEmail(), patientNovi.getAddress(), patientNovi.getCity(), patientNovi.getCountry(), patientNovi.getMobileNumber(), patientNovi.getJmbg());

		//patientService.save(patient);
		RequestUser user = new RequestUser(patientNovi.getUsername(), patientNovi.getPassword(), patientNovi.getFirstName(), patientNovi.getLastName(), patientNovi.getEmail(), patientNovi.getAddress(), patientNovi.getCity(), patientNovi.getCountry(), patientNovi.getMobileNumber(), patientNovi.getJmbg());
		requestService.save(user);
		System.out.println(user.getUsername());
		return new ResponseEntity<>(user, HttpStatus.OK);

	}
	@CrossOrigin
	@GetMapping("pacijenti")
	public List<Patient> getPatients(){
		return patientService.findAll();
	}
}
