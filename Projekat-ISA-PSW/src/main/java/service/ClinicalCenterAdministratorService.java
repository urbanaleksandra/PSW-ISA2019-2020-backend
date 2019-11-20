package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.ClinicalCenterAdministrator;
import repository.ClinicalCenterAdministratorRepository;

@Service
public class ClinicalCenterAdministratorService {
	
	@Autowired
	private ClinicalCenterAdministratorRepository clinicalCentreAdministratorRepository;
	
	public  ClinicalCenterAdministrator findByUsername(String username) {
		
		return clinicalCentreAdministratorRepository.findByUsername(username);
	}
	
	public ClinicalCenterAdministrator save(ClinicalCenterAdministrator cca) {
		
		return clinicalCentreAdministratorRepository.save(cca);
	}

}


