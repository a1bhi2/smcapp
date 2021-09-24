package com.a1bhi2.stockmarketcharting.repository;


import com.a1bhi2.stockmarketcharting.model.Company;
import com.a1bhi2.stockmarketcharting.model.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyDAO extends JpaRepository<Company,Integer> {
    Optional<Company> findCompanyByCompanyName(String companyName);
    List<Company> findCompanyBySector(Sector sector);

}
