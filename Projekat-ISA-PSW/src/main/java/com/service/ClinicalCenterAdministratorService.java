package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.model.ClinicalCenterAdministrator;
import com.repository.ClinicalCenterAdministratorRepository;

@Service
public class ClinicalCenterAdministratorService implements UserDetailsService {
	
	@Autowired
	private ClinicalCenterAdministratorRepository clinicalCentreAdministratorRepository;
	
	public  ClinicalCenterAdministrator findByUsername(String username) {
		
		return clinicalCentreAdministratorRepository.findByUsername(username);
	}
	
	public ClinicalCenterAdministrator save(ClinicalCenterAdministrator cca) {
		
		return clinicalCentreAdministratorRepository.save(cca);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ClinicalCenterAdministrator admin = clinicalCentreAdministratorRepository.findByUsername(username);

		if (admin== null) {
			throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
		} else {
			return admin;
		}
	}

}


