package com.socgen.stockmarketcharting.controller;

import com.socgen.stockmarketcharting.model.Company;
import com.socgen.stockmarketcharting.model.IpoDetails;
import com.socgen.stockmarketcharting.model.Sector;
import com.socgen.stockmarketcharting.service.SectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("/sector")
public class SectorController {
    @Autowired
    private SectorService sectorService;

    @GetMapping("/get")
    public ResponseEntity<Sector> getByName(@RequestParam String name){
        try {
            Sector sector = sectorService.getSector(name);
            return new ResponseEntity<>(sector,HttpStatus.OK);
        }catch (ResponseStatusException responseStatusException){
            throw responseStatusException;
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Sector> create(@RequestBody Sector sector){
        try {
            Sector savedSector = sectorService.create(sector);
            return new ResponseEntity<>(savedSector,HttpStatus.OK);
        }catch (ResponseStatusException responseStatusException){
            throw responseStatusException;
        }
    }

    @GetMapping("/getcompanies")
    public ResponseEntity<List<Company>> getCompaniesBySectorName(@RequestParam String sectorName){
        try{
            List<Company> companies = sectorService.getCompanyBySector(sectorName);
            return new ResponseEntity<>(companies, HttpStatus.OK);
        }catch (ResponseStatusException responseStatusException){
            throw responseStatusException;
        }
    }
    @GetMapping("/getall")
    public ResponseEntity<List<Sector>> getCompaniesBySectorName(){
        try{
            List<Sector> sectors = sectorService.getAll();
            return new ResponseEntity<>(sectors, HttpStatus.OK);
        }catch (ResponseStatusException responseStatusException){
            throw responseStatusException;
        }
    }
    @DeleteMapping("/delete")
    public void delete(@RequestBody Sector sector){
        sectorService.delete(sector);
    }

}
