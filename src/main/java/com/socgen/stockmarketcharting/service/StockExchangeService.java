package com.socgen.stockmarketcharting.service;


import com.socgen.stockmarketcharting.model.*;
import com.socgen.stockmarketcharting.repository.CompanyInStockExchangeDAO;
import com.socgen.stockmarketcharting.repository.StockExchangeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StockExchangeService {
    @Autowired
    private StockExchangeDAO stockExchangeDAO;
    @Autowired
    private CompanyInStockExchangeDAO companyInStockExchangeDAO;
    @Autowired
    private CompanyService companyService;




    public StockExchange createStockExchange(StockExchange stockExchange){
        Optional<StockExchange> checkStockExchange =  stockExchangeDAO.findStockExchangeByStockExchange(stockExchange.getStockExchange());
        if (checkStockExchange.isPresent())
            throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED);
        return stockExchangeDAO.save(stockExchange);
    }

    public List<StockExchange> getAll(){
        return stockExchangeDAO.findAll();
    }

    public List<Company> companiesInStockExchange(String name){
        List<CompanyInStockExchange> companies = companyInStockExchangeDAO.findCompaniesInStockExchange(name);
        List<Company> companyList = new ArrayList<>();
        for(CompanyInStockExchange company:companies){
            CompanyIOData companyIOData = companyService.getCompany(company.getCompanyId());
            companyList.add(companyIOData.getCompany());
        }
        return companyList;
    }

    public StockExchange getById(Integer id){
        Optional<StockExchange> stockExchange = stockExchangeDAO.findById(id);
        if(stockExchange.isPresent()){
            return stockExchange.get();
        }
        return null;
    }
    public void delete(StockExchange stockExchange){
        stockExchangeDAO.delete(stockExchange);
    }
}
