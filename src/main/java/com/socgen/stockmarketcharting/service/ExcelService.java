package com.socgen.stockmarketcharting.service;


import com.socgen.stockmarketcharting.helper.ExcelHelper;
import com.socgen.stockmarketcharting.model.StockPriceEntity;
import com.socgen.stockmarketcharting.repository.StockPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ExcelService {
    @Autowired
    StockPriceRepository repository;

    public void save(MultipartFile file) {
        try {
            List<StockPriceEntity> stockPrices = ExcelHelper.excelToStockPrice(file.getInputStream());
            repository.saveAll(stockPrices);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    public List<StockPriceEntity> getStockPrice() {

        return repository.findAll();
    }
    public void delete(String companyCode){
        repository.deleteAllByCompanyCode(companyCode);
    }

}
