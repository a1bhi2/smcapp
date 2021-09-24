package com.a1bhi2.stockmarketcharting.repository;


import com.a1bhi2.stockmarketcharting.model.StockPriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockPriceRepository extends JpaRepository<StockPriceEntity, Long> {

    void deleteAllByCompanyCode(String companyCode);

}
