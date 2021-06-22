package com.socgen.stockmarketcharting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class StockMarketChartingApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockMarketChartingApplication.class, args);
    }


}
