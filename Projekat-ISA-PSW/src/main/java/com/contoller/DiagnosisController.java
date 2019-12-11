package com.contoller;

import com.dto.DiagnosisDTO;
import com.model.Diagnosis;
import com.service.DiagnosisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class DiagnosisController {

    @Autowired
    private DiagnosisService diagnosisService;

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/api/add-diagnosis", method= RequestMethod.POST)
    public void saveDiagnosis(@RequestBody DiagnosisDTO diagnosisDTO){
        System.out.println((diagnosisDTO));
        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setName(diagnosisDTO.getName());
        diagnosis.setDescription(diagnosisDTO.getDescription());
        diagnosis = diagnosisService.save(diagnosis);

    }


}
