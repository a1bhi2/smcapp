package com.a1bhi2.stockmarketcharting.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class IpoDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String companyName;
    @ManyToOne
    private StockExchange stockExchange;
    private Double pricePerShare;
    private Integer totalNumberOfShares;
    
    private Date openDateTime;
    private String remarks;

}
