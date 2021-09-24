package com.a1bhi2.stockmarketcharting.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="StockExchange")
@Getter
@Setter
@NoArgsConstructor
public class StockExchange {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String stockExchange;
    private String brief;
    private String contactAddress;
    private String remarks;
}
