package com.contoller;

import com.dto.ClinicalCenterAdministratorDTO;
import com.model.ClinicalCenter;
import com.model.ClinicalCenterAdministrator;
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
public class ClinicalCenterAdministratorContoller {
	
	@Autowired
	private ClinicalCenterAdministratorService clinicalCenterAdministratorService;

	@Autowired
	private RequestService requestService;

	@Autowired
	private PatientService patientService;
	 
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/findByUsernameAndPassword")
	public ResponseEntity<ClinicalCenterAdministratorDTO> getCCAByUsernameAndPassword(@RequestParam String username){
		
		ClinicalCenterAdministrator cca = clinicalCenterAdministratorService.findByUsername(username);
		if(cca == null) {
			System.out.println("radi molim teeee i ovako bar" + username);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		System.out.println("radi molim teeee" + username);
		return new ResponseEntity<>(new ClinicalCenterAdministratorDTO(cca), HttpStatus.OK);
		
	}
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity<ClinicalCenterAdministratorDTO> saveCCA(@RequestBody ClinicalCenterAdministratorDTO ccaDTO){
		
		ClinicalCenterAdministrator cca = new ClinicalCenterAdministrator();
		
		cca.setUsername(ccaDTO.getUsername());
		cca.setPassword(ccaDTO.getPassword());
		ClinicalCenter cc = new ClinicalCenter();
		cc.setName(ccaDTO.getCenterDTO().getName());
		cca.setClinicalCenter(cc);
		
		if(cca == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		clinicalCenterAdministratorService.save(cca);
		
		return new ResponseEntity<>(new ClinicalCenterAdministratorDTO(cca), HttpStatus.OK);
		
	}

	@RequestMapping(value="/api/requests", method = RequestMethod.GET)
	public List<RequestUser> getPatientRequests(){

		List<RequestUser> requests  = requestService.findAll();
		System.out.println(requests.size());
		return requests;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value="/api/deny-request", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public  @ResponseBody ResponseEntity<RequestUser> deletePatientRequests(@RequestBody RequestUser deleteUser){

		requestService.delete(deleteUser);
		List<RequestUser> requests  = requestService.findAll();

		return new ResponseEntity<>(deleteUser, HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value="/api/accept-request", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public  @ResponseBody ResponseEntity<RequestUser> acceptPatientRequests(@RequestBody RequestUser deleteUser){

		requestService.delete(deleteUser);
		List<RequestUser> requests  = requestService.findAll();

		Patient patient = new Patient(deleteUser.getUsername(), deleteUser.getPassword(), deleteUser.getFirstName(), deleteUser.getLastName(), deleteUser.getEmail(), deleteUser.getAddress(), deleteUser.getCity(), deleteUser.getCountry(), deleteUser.getMobileNumber(), deleteUser.getJmbg());

		patientService.save(patient);
		return new ResponseEntity<>(deleteUser, HttpStatus.OK);
	}


}
