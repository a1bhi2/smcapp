package com.socgen.stockmarketcharting.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CompanyInStockExchange {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String stockExchangeName;
    private Integer companyId;
    private String companyStockExchangeCode;

}
