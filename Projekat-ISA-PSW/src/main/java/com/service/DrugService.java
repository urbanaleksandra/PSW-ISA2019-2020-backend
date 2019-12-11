package com.service;

import com.model.Drug;
import com.repository.DrugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DrugService {

    @Autowired
    private DrugRepository drugRepository;

    public Drug save(Drug drug){
        return drugRepository.save(drug);
    }
}
