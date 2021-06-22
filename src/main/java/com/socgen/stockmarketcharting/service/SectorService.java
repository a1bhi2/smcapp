package com.socgen.stockmarketcharting.service;

import com.socgen.stockmarketcharting.model.*;
import com.socgen.stockmarketcharting.repository.CompanyDAO;
import com.socgen.stockmarketcharting.repository.SectorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SectorService {
    @Autowired
    private SectorDAO sectorDAO;
    @Autowired
    private CompanyDAO companyDAO;


    public Sector getSector(String name){
        Sector sector = sectorDAO.findSectorBySectorName(name);
        if(sector == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return sector;
    }
    public Sector create(Sector sector){
        Sector checkSector = sectorDAO.findSectorBySectorName(sector.getSectorName());
        if(checkSector!=null)
            throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED);
        return sectorDAO.save(sector);
    }
    public List<Company> getCompanyBySector(String sectorName){
        Sector sector = sectorDAO.findSectorBySectorName(sectorName);
        return companyDAO.findCompanyBySector(sector);
    }
    public List<Sector> getAll(){
        return sectorDAO.findAll();
    }
}
