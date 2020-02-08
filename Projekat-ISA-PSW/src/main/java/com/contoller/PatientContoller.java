package com.contoller;

import com.dto.PatientDTO;
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

import java.util.ArrayList;
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
	public @ResponseBody ResponseEntity<RequestUser> registrujPacijenta(@RequestBody PatientDTO patientNovi){
		
		//Patient patient = new Patient(patientNovi.getUsername(), patientNovi.getPassword(), patientNovi.getFirstName(), patientNovi.getLastName(), patientNovi.getEmail(), patientNovi.getAddress(), patientNovi.getCity(), patientNovi.getCountry(), patientNovi.getMobileNumber(), patientNovi.getJmbg());

		//patientService.save(patient);
		RequestUser user = new RequestUser(patientNovi.getUsername(), patientNovi.getPassword(), patientNovi.getFirstName(), patientNovi.getLastName(), patientNovi.getEmail(), patientNovi.getAddress(), patientNovi.getCity(), patientNovi.getCountry(), patientNovi.getMobileNumber(), patientNovi.getJmbg());
		requestService.save(user);
		System.out.println("USAO U REGISTER" + user.getUsername());
		System.out.println(user.getUsername());
		return new ResponseEntity<>(user, HttpStatus.OK);

	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value="/changePatientInfo", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Patient> changePatientInfo(@RequestBody PatientDTO patientNovi){

		Patient patient1 = patientService.findByUsername(patientNovi.getUsername());
		if(patient1 != null){
			patient1.setFirstName(patientNovi.getFirstName());
			patient1.setLastName(patientNovi.getLastName());
			patient1.setCity(patientNovi.getCity());
			patient1.setCountry(patientNovi.getCountry());
			patient1.setAddress(patientNovi.getAddress());
			patient1.setMobileNumber(patientNovi.getMobileNumber());

			patientService.save(patient1);
		}
		else{
			Patient patient = new Patient(patientNovi.getUsername(), patientNovi.getPassword(), patientNovi.getFirstName(), patientNovi.getLastName(), patientNovi.getEmail(), patientNovi.getAddress(), patientNovi.getCity(), patientNovi.getCountry(), patientNovi.getMobileNumber(), patientNovi.getJmbg());

			patientService.save(patient);
		}
		Patient patient3 = new Patient();
		return new ResponseEntity<>(patient3, HttpStatus.OK);

	}

	@CrossOrigin
	@GetMapping("pacijenti")
	public List<PatientDTO> getPatients(){
		List<PatientDTO> patientDTOS = new ArrayList<>();
		for (Patient patient:patientService.findAll()){
			PatientDTO patientDTO = new PatientDTO();
			patientDTO.setFirstName(patient.getFirstName());
			patientDTO.setLastName(patient.getLastName());
			patientDTO.setAddress(patient.getAddress());
			patientDTO.setCity(patient.getCity());
			patientDTO.setCountry(patient.getCountry());
			patientDTO.setEmail(patient.getEmail());
			patientDTO.setId(patient.getId());
			patientDTO.setJmbg(patient.getJmbg());
			patientDTO.setMobileNumber(patient.getMobileNumber());
			patientDTO.setUsername(patient.getUsername());
			patientDTO.setPassword(patient.getPassword());
			patientDTOS.add(patientDTO);
		}

		return patientDTOS;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value="/pacijent/{username}", method= RequestMethod.GET)
	public PatientDTO getPatient(@PathVariable String username){

		Patient patient = patientService.findByUsername(username);
		PatientDTO patientDTO = new PatientDTO();
		patientDTO.setFirstName(patient.getFirstName());
		patientDTO.setLastName(patient.getLastName());
		patientDTO.setAddress(patient.getAddress());
		patientDTO.setCity(patient.getCity());
		patientDTO.setCountry(patient.getCountry());
		patientDTO.setEmail(patient.getEmail());
		patientDTO.setId(patient.getId());
		patientDTO.setJmbg(patient.getJmbg());
		patientDTO.setMobileNumber(patient.getMobileNumber());
		patientDTO.setUsername(patient.getUsername());
		patientDTO.setPassword(patient.getPassword());

		System.out.println(patient.getFirstName());
		return patientDTO;
	}
}
