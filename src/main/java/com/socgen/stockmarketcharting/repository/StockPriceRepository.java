package com.socgen.stockmarketcharting.repository;


import com.socgen.stockmarketcharting.model.StockPriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockPriceRepository extends JpaRepository<StockPriceEntity, Long> {

    void deleteAllByCompanyCode(String companyCode);

}
