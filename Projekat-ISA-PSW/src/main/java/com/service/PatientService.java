package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Patient;
import com.repository.PatientRepository;

import java.util.List;

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
	public List<Patient> findAll() {
		return patientRepository.findAll();

	}

}
