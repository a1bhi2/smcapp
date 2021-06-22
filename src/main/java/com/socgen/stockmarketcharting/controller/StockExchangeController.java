package com.socgen.stockmarketcharting.controller;


import com.socgen.stockmarketcharting.model.Company;
import com.socgen.stockmarketcharting.model.StockExchange;
import com.socgen.stockmarketcharting.service.StockExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/stockexchange")
public class StockExchangeController {
    @Autowired
    private StockExchangeService stockExchangeService;


    @PostMapping("/create")
    public ResponseEntity<StockExchange> createStockExchange(@RequestBody StockExchange newStockExchange){
        try {


            return new ResponseEntity<>(stockExchangeService.createStockExchange(newStockExchange),HttpStatus.OK);
        }catch (ResponseStatusException responseStatusException){
            throw responseStatusException;
        }
    }

    @GetMapping("/getall")
    public ResponseEntity<List<StockExchange>> getAll(){
        try {
            return new ResponseEntity<>(stockExchangeService.getAll(),HttpStatus.OK);
        }catch (Exception e){
            throw e;
        }
    }

    @GetMapping("/getcompanies")
    public ResponseEntity<List<Company>> getCompanies(@RequestParam String stockExchangeName){
        try {
            return new ResponseEntity<>(stockExchangeService.companiesInStockExchange(stockExchangeName),HttpStatus.OK);
        }catch (ResponseStatusException responseStatusException){
            throw responseStatusException;
        }
    }

    @GetMapping("/getbyid")
    public ResponseEntity<StockExchange> getById(@RequestParam Integer id){
        try {
            StockExchange stockExchange = stockExchangeService.getById(id);
            if(stockExchange == null){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(stockExchange,HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
