package com.socgen.stockmarketcharting.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="StockPriceEntity")
@Getter
@Setter
public class StockPriceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String companyCode;
    private String stockExchange;
    private double currentPrice;
    private Date date;
    private String time;

    public StockPriceEntity(Long id, String companyCode, String stockExchange, double currentPrice, Date date, String time) {
        this.id = id;
        this.companyCode = companyCode;
        this.stockExchange = stockExchange;
        this.currentPrice = currentPrice;
        this.date = date;
        this.time = time;
    }

    public StockPriceEntity() {

    }
}
