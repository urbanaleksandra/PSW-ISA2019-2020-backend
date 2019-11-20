package contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import model.Patient;
import service.PatientService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class PatientContoller {
	
	@Autowired
	private PatientService patientService;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value="/register", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Patient> registrujPacijenta(@RequestBody Patient patientNovi){
		
		Patient patient = new Patient(patientNovi.getUsername(), patientNovi.getPassword(), patientNovi.getFirstName(), patientNovi.getLastName(), patientNovi.getEmail(), patientNovi.getAdress(), patientNovi.getCity(), patientNovi.getCountry(), patientNovi.getMobileNumber(), patientNovi.getJmbg());
		System.out.println(patient.getUsername());
		patientService.save(patient);
		
		return new ResponseEntity<>(patient, HttpStatus.OK);
	}
}
