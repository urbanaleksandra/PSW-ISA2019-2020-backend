package com.service;

import com.model.RequestUser;
import com.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    public RequestUser save(RequestUser user){
        return requestRepository.save(user);
    }

    public void delete(RequestUser user){
        requestRepository.delete(user);
    }

    public List<RequestUser> findAll(){
        return requestRepository.findAll();
    }




}
