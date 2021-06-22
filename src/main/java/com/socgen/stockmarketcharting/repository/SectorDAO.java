package com.socgen.stockmarketcharting.repository;


import com.socgen.stockmarketcharting.model.Sector;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectorDAO extends JpaRepository<Sector,Integer> {
    Sector findSectorBySectorName(String sectorName);
}
