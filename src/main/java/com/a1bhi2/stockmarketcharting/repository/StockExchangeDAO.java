package com.a1bhi2.stockmarketcharting.repository;

import com.a1bhi2.stockmarketcharting.model.StockExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockExchangeDAO extends JpaRepository<StockExchange,Integer> {
    Optional<StockExchange>findStockExchangeByStockExchange(String stockExchange);
}
