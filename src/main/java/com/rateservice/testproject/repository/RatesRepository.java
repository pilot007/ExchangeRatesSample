package com.rateservice.testproject.repository;

import com.rateservice.testproject.datamodel.Rates;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface  RatesRepository  extends CrudRepository<Rates, Long> {
    Rates findByExchange1AndExchange2(String exchange1,String exchange2);

    List<Rates> findByExchange1In (List<String> exchanges1);

    Rates findByExchange1(String exchange1);
}
