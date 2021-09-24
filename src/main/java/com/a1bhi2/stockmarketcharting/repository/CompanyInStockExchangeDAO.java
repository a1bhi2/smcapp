package com.a1bhi2.stockmarketcharting.repository;

import com.a1bhi2.stockmarketcharting.model.CompanyInStockExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyInStockExchangeDAO extends JpaRepository<CompanyInStockExchange,Integer> {
    @Query("SELECT c from CompanyInStockExchange c WHERE c.companyId= :id ")
    List<CompanyInStockExchange> findStockExchangeCode(@Param("id") Integer companyId);

    @Query("select c.companyStockExchangeCode from CompanyInStockExchange c where c.companyId= :companyId" +
            " and c.stockExchangeName= :stockExchangeName")
    String findCompanyStockExchangeCode(@Param("companyId") Integer companyId,
                                        @Param("stockExchangeName") String stockExchangeName);

    @Query("Select c from CompanyInStockExchange c where c.stockExchangeName=:name")
    List<CompanyInStockExchange> findCompaniesInStockExchange(@Param("name") String name);

    void deleteAllByCompanyId(Integer companyId);
}
