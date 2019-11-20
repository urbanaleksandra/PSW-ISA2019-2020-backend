package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Patient;
import repository.PatientRepository;

@Service
public class PatientService {
	
	@Autowired
	private PatientRepository patientRepository;
	
	public Patient findByUsername(String username) {
		return patientRepository.findByUsername(username);
	}
	
	public Patient save(Patient patient) {
		return patientRepository.save(patient);
		
	}

}
