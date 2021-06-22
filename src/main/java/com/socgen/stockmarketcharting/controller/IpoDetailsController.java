package com.socgen.stockmarketcharting.controller;

import com.socgen.stockmarketcharting.model.IpoDetails;
import com.socgen.stockmarketcharting.service.IpoDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/ipo")
public class IpoDetailsController {
    @Autowired
    private IpoDetailsService ipoDetailsService;

    @PostMapping("/create")
    public ResponseEntity<IpoDetails> createNewIpo(@RequestBody IpoDetails ipoDetails){
        try {
            IpoDetails createdIpoDetails = ipoDetailsService.createNewIpo(ipoDetails);
            return new ResponseEntity<>(createdIpoDetails, HttpStatus.OK);
        }
        catch (ResponseStatusException responseStatusException){
            throw responseStatusException;
        }

    }

    @GetMapping("/all")
    public List<IpoDetails> getAll(){
        return ipoDetailsService.getAll();
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody IpoDetails ipoDetails){
        ipoDetailsService.delete(ipoDetails);
    }
}

