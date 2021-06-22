package com.socgen.stockmarketcharting.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CompanyIOData {
    private Company company;
    private List<String> companyStockExchangeCodes;
}
