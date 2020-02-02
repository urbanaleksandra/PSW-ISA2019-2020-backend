package com.service;

import com.model.PriceList;
import com.repository.PriceListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PriceListService {

    @Autowired
    private PriceListRepository priceListRepository;

    public PriceList save(PriceList priceList) {
        return priceListRepository.save(priceList);

    }
    public List<PriceList> findAll() {
        return priceListRepository.findAll();

    }

    public Optional<PriceList> findById(Long id){
        return priceListRepository.findById(id);
    }
}
