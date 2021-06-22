package com.socgen.stockmarketcharting.repository;


import com.socgen.stockmarketcharting.model.IpoDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IpoDetailsDAO extends JpaRepository<IpoDetails,Integer> {

}
