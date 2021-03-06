package com.a1bhi2.stockmarketcharting.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class StockPriceRequest {
    private String companyCode;
    private Date from;
    private Date to;
}
