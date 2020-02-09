package com.contoller;

import com.dto.ClinicalCenterAdministratorDTO;
import com.dto.PatientDTO;
import com.model.*;
import com.repository.ClinicAdministratorRepository;
import com.repository.ClinicalCenterAdministratorRepository;
import com.repository.ConfirmationTokenRepository;
import com.security.JwtAuthenticationRequest;
import com.security.TokenUtils;
import com.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.AuthenticationException;
import javax.print.DocFlavor;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ClinicalCenterAdministratorContoller {
	
	@Autowired
	private ClinicalCenterAdministratorService clinicalCenterAdministratorService;

	@Autowired
	private RequestService requestService;

	@Autowired
	private TokenUtils tokenUtils;

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
	private ConfirmationTokenRepository confirmationTokenRepository;

	@Autowired
	private ClinicalCenterAdministratorRepository clinicalCenterAdministratorRepository;

	@Autowired
	private EmailService emailService;

	@Autowired
	private MedicalRecordService medicalRecordService;

	@Autowired
	private ClinicalCenterAdministratorService service;
	 
	@CrossOrigin(origins = "http://localhost:4200")
	//@PostMapping(value = "/findByUsernameAndPassword")
	@RequestMapping(value="/auth/login", method= RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> postCCAByUsernameAndPassword(@RequestBody JwtAuthenticationRequest authenticationRequest,
														  HttpServletResponse response) throws AuthenticationException, IOException {

		AuthenticationManager a = new AuthenticationManager() {
			@Override
			public Authentication authenticate(Authentication authentication) throws org.springframework.security.core.AuthenticationException {
				String name = authentication.getName();
				String password = authentication.getCredentials().toString();
				return new UsernamePasswordAuthenticationToken(
						name, password, new ArrayList<>());
			}
		};

		final Authentication authentication = a.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
				authenticationRequest.getPassword()));
//
		SecurityContextHolder.getContext().setAuthentication(authentication);

		System.out.println("usao");
		//ClinicalCenterAdministrator cca = clinicalCenterAdministratorService.findByUsername(authenticationRequest.getUsername());
//		String ret = "none";
		String jwt = "";
		Patient pa = null;
		ClinicAdministrator ca = null;
		Doctor doc = null;
		Nurse nur = null;
		String username  = (String) authentication.getPrincipal();
		ClinicalCenterAdministrator cca = (ClinicalCenterAdministrator) clinicalCenterAdministratorService.findByUsername(username);
       if(cca == null) {

			pa = patientService.findByUsername(username);
			if(pa == null){

					ca = clinicAdministratorService.findByUsername(username);
					if(ca == null) {

						doc = doctorService.findByUsername(username);
						if(doc == null) {

							nur = nurseService.findByUsername(username);
							if(nur == null){
								jwt = "";
							}
							else{
								jwt = tokenUtils.generateToken(username);
							}

						}
						else{
							jwt = tokenUtils.generateToken(username);
						}
					}
					else{
						jwt = tokenUtils.generateToken(username);
					}
			}
			else{
				jwt = tokenUtils.generateToken(username);
			}
		}
		else{
		   jwt = tokenUtils.generateToken(username);
		}

		int expiresIn = tokenUtils.getExpiredIn();
//		System.out.println(new UserTokenState(jwt, expiresIn));

		return ResponseEntity.ok(new UserTokenState(jwt, expiresIn));
		//return ret;
		
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
		MedicalRecord medicalRecord = new MedicalRecord();
		patient.setRecord(medicalRecord);
		patient = patientService.save(patient);
		medicalRecord.setPatient(patient);
		patient.setRecord(medicalRecord);
		patient = patientService.save(patient);

		ConfirmationTokenRegistration confirmationToken = new ConfirmationTokenRegistration(patient);
		confirmationToken.setPatientUsername(patient.getUsername());
		confirmationTokenRepository.save(confirmationToken);

		//slanje emaila
		try {
			emailService.sendNotificaitionAsync(patient, confirmationToken);
		}catch( Exception e ){
			System.out.println("nije poslata poruka");
		}
		return new ResponseEntity<>(deleteUser, HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value="/confirmAccount/{token}", method= RequestMethod.GET)
	public  Patient  confirmAccount(ModelAndView modelAndView, @PathVariable("token") String confirmationToken){

		//Patient patient = new Patient(patientDTO.getUsername(), patientDTO.getPassword(), patientDTO.getFirstName(), patientDTO.getLastName(), patientDTO.getEmail(), patientDTO.getAddress(), patientDTO.getCity(), patientDTO.getCountry(), patientDTO.getMobileNumber(), patientDTO.getJmbg());
		//System.out.println(patient.getFirstName());
		ConfirmationTokenRegistration token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
		Patient patient = patientService.findByUsername(token.getPatient().getUsername());
		System.out.println("USAO U CONF ACC" + token.getPatient().getUsername());

		patient.setEnabled(true);
		patient = patientService.save(patient);

		return token.getPatient();
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value="/api/add-clinic-center-admin", method= RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public void addAdministrator(@RequestBody ClinicalCenterAdministrator clinicalCenterAdministrator){
		System.out.println(clinicalCenterAdministrator);
		clinicalCenterAdministratorRepository.save(clinicalCenterAdministrator);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value="/api/get-admin/{username}", method= RequestMethod.GET)
	private ResponseEntity  getAdmin(@PathVariable String username){
		if(username != null) {
			ClinicalCenterAdministrator cca = service.findByUsername(username);
			if (cca != null) {
				return new ResponseEntity<>(cca, HttpStatus.OK);
			}
			return new ResponseEntity<>("No admin with this username", HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<>("username is null!",
				HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value="api/set-password-admin/{password}/{username}", method= RequestMethod.GET)
	private ClinicalCenterAdministrator setPasswordAdmin(@PathVariable("username") String username, @PathVariable("password") String password){
		ClinicalCenterAdministrator cca = service.findByUsername(username);
		cca.setPassword(password);
		cca.setFirstLog(1);
		ClinicalCenterAdministrator newcca = service.save(cca);
		return newcca;
	}


}
