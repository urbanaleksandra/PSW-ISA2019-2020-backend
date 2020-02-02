package com.contoller;

import com.dto.PriceListDTO;
import com.model.AppointmentType;
import com.model.PriceList;
import com.service.PriceListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class PriceListController {

    @Autowired
    private PriceListService service;


    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/getPrices", method= RequestMethod.GET)
    public List<PriceList> getPrices(){
        List<PriceList> list=service.findAll();
        System.out.println(list.get(0).getPrice());
        return service.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/add-price", method= RequestMethod.POST)
    public void addPrice(@RequestBody PriceList priceList){
        System.out.println(("asaaaaaaa"));
        service.save(priceList);

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/changePrice", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<PriceListDTO> changeInfo(@RequestBody PriceListDTO priceList){
        System.out.println(priceList.getId());
        System.out.println("asdsdas");
        Optional<PriceList> priceList1 = service.findById(priceList.getId());
        PriceList priceList2=priceList1.get();
        if(priceList2 != null){
            System.out.println(priceList2);
            priceList2.setPrice(priceList.getPrice());
            service.save(priceList2);
        }
        else{
        }
        PriceListDTO p=new PriceListDTO();
        return new ResponseEntity<>(p, HttpStatus.OK);

    }

}
