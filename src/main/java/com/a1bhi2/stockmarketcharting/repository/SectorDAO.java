package com.a1bhi2.stockmarketcharting.repository;


import com.a1bhi2.stockmarketcharting.model.Sector;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectorDAO extends JpaRepository<Sector,Integer> {
    Sector findSectorBySectorName(String sectorName);
}
