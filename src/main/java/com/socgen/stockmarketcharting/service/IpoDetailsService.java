package com.socgen.stockmarketcharting.service;


import com.socgen.stockmarketcharting.model.IpoDetails;
import com.socgen.stockmarketcharting.repository.IpoDetailsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IpoDetailsService {
    @Autowired
    private IpoDetailsDAO ipoDetailsDAO;

    public IpoDetails createNewIpo(IpoDetails ipoDetails){
        return ipoDetailsDAO.save(ipoDetails);
    }
    public List<IpoDetails> getAll(){
        return ipoDetailsDAO.findAll();
    }
}
