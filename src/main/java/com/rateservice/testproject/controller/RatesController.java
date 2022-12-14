package com.rateservice.testproject.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.rateservice.testproject.datamodel.Rates;
import com.rateservice.testproject.repository.RatesRepository;
import com.rateservice.testproject.utils.RatesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/rates")
public class RatesController {

    @Autowired
    private RatesRepository ratesRepository;

    //URL
    //http://localhost:8081/api/rates/findExchange/USD/EUR
    @GetMapping("/findExchange/{exchange1}/{exchange2}")
    public Rates findExchange(@PathVariable String exchange1,@PathVariable String exchange2) {
        Rates rt = ratesRepository.findByExchange1AndExchange2 (exchange1,exchange2);
        return rt;
    }

    //URL
    //http://localhost:8081/api/rates/exchange/EUR
    @GetMapping("/exchange/{exchange}")
    public ResponseEntity<?> fetchExchangeFromExternalSystem(@PathVariable String exchange) {

        RatesUtil.FetchRates(exchange,ratesRepository);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    //URL
    //http://localhost:8081/api/rates/createXmlFile/USD,GBP,EUR,CHF
    @GetMapping(value="/createXmlFile/{exchange1}",produces = { "application/xml", "text/xml" })
    public List<Rates> createXMLFile(@PathVariable List<String> exchange1) throws JsonProcessingException {
        List<Rates> rt = ratesRepository.findByExchange1In (exchange1);
        ObjectMapper xmlMapper = new XmlMapper();
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String XMLStr = xmlMapper.writeValueAsString(rt);
        RatesUtil.createXML(XMLStr);
        return rt;
    }


}
