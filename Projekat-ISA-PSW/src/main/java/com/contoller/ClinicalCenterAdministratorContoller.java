package com.contoller;

import com.dto.ClinicalCenterAdministratorDTO;
import com.model.*;
import com.repository.ClinicAdministratorRepository;
import com.repository.ClinicalCenterAdministratorRepository;
import com.security.JwtAuthenticationRequest;
import com.security.TokenUtils;
import com.service.ClinicalCenterAdministratorService;
import com.service.EmailService;
import com.service.PatientService;
import com.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import javax.print.DocFlavor;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ClinicalCenterAdministratorContoller {
	
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
	private ClinicalCenterAdministratorRepository clinicalCenterAdministratorRepository;

	@Autowired
	private EmailService emailService;
	 
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(value = "/findByUsernameAndPassword")
	public ResponseEntity<?> postCCAByUsernameAndPassword(@RequestBody JwtAuthenticationRequest authenticationRequest,
														  HttpServletResponse response) throws AuthenticationException, IOException {


//		final Authentication authentication;
//		authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
//				authenticationRequest.getPassword()));
//
//		SecurityContextHolder.getContext().setAuthentication(authentication);

//		ClinicalCenterAdministrator cca = clinicalCenterAdministratorService.findByUsername(username);
////		if(cca == null) {
////			System.out.println("radi molim teeee i ovako bar" + username);
////			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
////		}
////		System.out.println("radi molim teeee" + username);

		ClinicalCenterAdministrator cca = clinicalCenterAdministratorService.findByUsername(authenticationRequest.getUsername());
		if(cca == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		System.out.println(authenticationRequest.getUsername());
		System.out.println(cca.getUsername());
		String jwt = tokenUtils.generateToken(cca.getUsername()); //user.username
		int expiresIn = tokenUtils.getExpiredIn();
		System.out.println(new UserTokenState(jwt, expiresIn));
		return ResponseEntity.ok(new UserTokenState(jwt, expiresIn));
		
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
		Patient patient = new Patient(deleteUser.getUsername(), deleteUser.getPassword(), deleteUser.getFirstName(), deleteUser.getLastName(), deleteUser.getEmail(), deleteUser.getAddress(), deleteUser.getCity(), deleteUser.getCountry(), deleteUser.getMobileNumber(), deleteUser.getJmbg());


		return new ResponseEntity<>(deleteUser, HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value="/api/deny-request-message/{email}", method=RequestMethod.POST)
	public  void deletePatientRequests(@RequestBody String message, @PathVariable String email){
		System.out.println("usao da salje mejl novi");
		try {
			emailService.sendNotificaitionAsync2(email, message);
		}catch( Exception e ) {
			System.out.println("nije poslata poruka");
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value="/api/accept-request", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public  @ResponseBody ResponseEntity<RequestUser> acceptPatientRequests(@RequestBody RequestUser deleteUser){

		requestService.delete(deleteUser);
		List<RequestUser> requests  = requestService.findAll();

		Patient patient = new Patient(deleteUser.getUsername(), deleteUser.getPassword(), deleteUser.getFirstName(), deleteUser.getLastName(), deleteUser.getEmail(), deleteUser.getAddress(), deleteUser.getCity(), deleteUser.getCountry(), deleteUser.getMobileNumber(), deleteUser.getJmbg());

		patientService.save(patient);

		//slanje emaila
		try {
			emailService.sendNotificaitionAsync(patient);
		}catch( Exception e ){
			System.out.println("nije poslata poruka");
		}
		return new ResponseEntity<>(deleteUser, HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value="/api/add-clinic-center-admin", method= RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public void addAdministrator(@RequestBody ClinicalCenterAdministrator clinicalCenterAdministrator){
		clinicalCenterAdministratorRepository.save(clinicalCenterAdministrator);
	}


}
