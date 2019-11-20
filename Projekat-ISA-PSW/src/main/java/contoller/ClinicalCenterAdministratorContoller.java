package contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dto.ClinicalCenterAdministratorDTO;
import model.ClinicalCenter;
import model.ClinicalCenterAdministrator;
import service.ClinicalCenterAdministratorService;

@RestController
public class ClinicalCenterAdministratorContoller {
	
	@Autowired
	private ClinicalCenterAdministratorService clinicalCenterAdministratorService;
	 
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/findByUsernameAndPassword")
	public ResponseEntity<ClinicalCenterAdministratorDTO> getCCAByUsernameAndPassword(@RequestParam String username, @RequestParam String password){
		
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

}
