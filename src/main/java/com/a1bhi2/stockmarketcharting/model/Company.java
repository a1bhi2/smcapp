package com.a1bhi2.stockmarketcharting.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Table(name = "company")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String companyName;
    private float turnover;
    private String ceo;
    private String boardOfDirectors;

    @ManyToMany
    private List<StockExchange> listedInStockExchange;
    @ManyToOne
    private Sector sector;
    private String briefWriteup;



}
