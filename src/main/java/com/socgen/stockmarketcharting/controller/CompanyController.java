package com.socgen.stockmarketcharting.controller;

import com.socgen.stockmarketcharting.model.*;
import com.socgen.stockmarketcharting.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/company")
//@PreAuthorize("hasRole('ROLE_ADMIN')")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @RequestMapping(value="/create", method=RequestMethod.POST)
    public ResponseEntity<Company> createCompany(@RequestBody CompanyIOData companyIOData){
        try{
            Company createdCompany = companyService.createCompany(companyIOData);
            return new ResponseEntity<>(createdCompany, HttpStatus.OK);
        }
        catch (ResponseStatusException e){
            throw e;
        }
    }

    @GetMapping("/getstockexchange")
    public ResponseEntity<List<StockExchange>> getStockExchange(@RequestParam int id){
        try {
            List<StockExchange> stockExchanges = companyService.getStockExchange(id);
            return new ResponseEntity<>(stockExchanges, HttpStatus.OK);
        }
        catch (ResponseStatusException responseStatusException) {
            throw responseStatusException;
        }
    }

    @GetMapping("/getbyid")
    public ResponseEntity<CompanyIOData> getCompany(@RequestParam Integer id){
        try {
            CompanyIOData companyIOData = companyService.getCompany(id);
            return new ResponseEntity<>(companyIOData,HttpStatus.OK);
        }
        catch (ResponseStatusException responseStatusException){
            throw responseStatusException;
        }
    }

    @PostMapping("/getpricebycode")
    public ResponseEntity<List<StockPriceEntity>> getPrice(@RequestBody StockPriceRequest stockPriceRequest){
        try {
            List<StockPriceEntity> stockPriceEntities = companyService.getStockPrices(stockPriceRequest);
            return new ResponseEntity<>(stockPriceEntities,HttpStatus.OK);
        }
        catch (ResponseStatusException responseStatusException){
            throw responseStatusException;
        }

    }

    @GetMapping("/getall")
    public ResponseEntity<List<Company>> getAll(){
        try{
            return new ResponseEntity<>(companyService.getAll(),HttpStatus.OK);
        }catch (ResponseStatusException responseStatusException){
            throw responseStatusException;
        }
    }
    @GetMapping("/{companyId}/{stockExchangeName}")
    public ResponseEntity<String> getCompanyCode(@PathVariable("companyId") Integer companyId,
                                                 @PathVariable("stockExchangeName") String stockExchangeName){
        try {
            return ResponseEntity.ok(companyService.getCompanyCode(companyId, stockExchangeName));
        }catch (ResponseStatusException responseStatusException){
            throw responseStatusException;
        }
        catch (Exception e)
        {
            throw e;
        }
    }
    @DeleteMapping("/delete")
    public void delete(@RequestBody Company company){
        companyService.delete(company);
    }
}
