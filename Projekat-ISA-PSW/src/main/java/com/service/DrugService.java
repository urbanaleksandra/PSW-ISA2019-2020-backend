package com.service;

import com.model.Drug;
import com.repository.DrugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrugService {

    @Autowired
    private DrugRepository drugRepository;

    public Drug save(Drug drug){
        return drugRepository.save(drug);
    }

    public List<Drug> findAll() { return drugRepository.findAll(); }

    public Drug findById(Long id) { return drugRepository.findById(id).get();}
}
