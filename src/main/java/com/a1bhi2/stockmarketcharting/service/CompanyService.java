package com.a1bhi2.stockmarketcharting.service;

import com.a1bhi2.stockmarketcharting.model.*;
import com.a1bhi2.stockmarketcharting.repository.CompanyDAO;
import com.a1bhi2.stockmarketcharting.repository.CompanyInStockExchangeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class CompanyService {
    @Autowired
    private CompanyDAO companyDAO;


    @Autowired
    private CompanyInStockExchangeDAO companyInStockExchangeDAO;
    @Autowired
    private ExcelService excelService;



    public Company createCompany(CompanyIOData newCompany){
        Optional<Company> companyOptional = companyDAO.findCompanyByCompanyName(newCompany.getCompany().getCompanyName());
        if(companyOptional.isPresent())
            throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED);
        Company savedCompany = companyDAO.save(newCompany.getCompany());
        List<StockExchange> listedInStockExchange = newCompany.getCompany().getListedInStockExchange();
        Iterator<StockExchange> itr1 = listedInStockExchange.iterator();
        Iterator<String> itr2 = newCompany.getCompanyStockExchangeCodes().iterator();
        while(itr1.hasNext() && itr2.hasNext()) {
            CompanyInStockExchange newListing = new CompanyInStockExchange();
            newListing.setStockExchangeName(itr1.next().getStockExchange());
            newListing.setCompanyId(savedCompany.getId());
            newListing.setCompanyStockExchangeCode(itr2.next());
            companyInStockExchangeDAO.save(newListing);
        }
        return savedCompany;
    }

    public List<StockExchange> getStockExchange(int companyId){
        Optional<Company> company = companyDAO.findById(companyId);
        if(company.isPresent())
            return company.get().getListedInStockExchange();
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public CompanyIOData getCompany(Integer id){
        CompanyIOData companyIOData = new CompanyIOData();
        Optional<Company> oCompany = companyDAO.findById(id);
        if (!oCompany.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        Company company = oCompany.get();
        companyIOData.setCompany(company);
        List<CompanyInStockExchange> companyInStockExchanges = companyInStockExchangeDAO.findStockExchangeCode(id);
        List<String> codes = new ArrayList<>();
        for(CompanyInStockExchange c:companyInStockExchanges){
            codes.add(c.getCompanyStockExchangeCode());
        }
        companyIOData.setCompanyStockExchangeCodes(codes);
        return companyIOData;
    }

    public List<StockPriceEntity> getStockPrices(StockPriceRequest stockPriceRequest){

        List<StockPriceEntity> stockprices = excelService.getStockPrice();
        List<StockPriceEntity> companyStockPrices = new ArrayList<>();
        Date from = removeTime(stockPriceRequest.getFrom());
        Date to = removeTime(stockPriceRequest.getTo());

        for(StockPriceEntity stockPriceEntity:stockprices){
            String code = stockPriceEntity.getCompanyCode().replaceAll("[^a-zA-Z0-9]", "");
            if(code.equalsIgnoreCase(stockPriceRequest.getCompanyCode())){
                if(!from.after(stockPriceEntity.getDate()) && !to.before(stockPriceEntity.getDate()))
                    companyStockPrices.add(stockPriceEntity);
            }
        }
        if(companyStockPrices.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return companyStockPrices;


    }

    public List<Company> getAll(){
        return companyDAO.findAll();
    }

    public static Date removeTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
    public String getCompanyCode(Integer companyId,String stockExchange){
        String code = companyInStockExchangeDAO.findCompanyStockExchangeCode(companyId,stockExchange);
        if(code.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return code;
    }

    public void delete(Company company){
        companyDAO.delete(company);
        companyInStockExchangeDAO.deleteAllByCompanyId(company.getId());
    }
}
