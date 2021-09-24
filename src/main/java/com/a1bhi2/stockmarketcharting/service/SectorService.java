package com.a1bhi2.stockmarketcharting.service;

import com.a1bhi2.stockmarketcharting.model.Company;
import com.a1bhi2.stockmarketcharting.model.Sector;
import com.a1bhi2.stockmarketcharting.repository.CompanyDAO;
import com.a1bhi2.stockmarketcharting.repository.SectorDAO;
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

    public void delete(Sector sector){
        sectorDAO.delete(sector);
    }
}
