package com.rateservice.testproject.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.rateservice.testproject.datamodel.Rates;
import com.rateservice.testproject.repository.RatesRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;

public  class RatesUtil {

    // Setting URL
    static String url_str = "https://v6.exchangerate-api.com/v6/b19acf49235675d6d6dffc0c/latest";

    public static void FetchRates(String exchange , RatesRepository ratesRepository)
    {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response
                = restTemplate.getForEntity(url_str + "/"+ exchange, String.class);

// Convert to JSON
        JsonParser jp = new JsonParser();
        JsonObject jsonobj = (JsonObject) jp.parse(response.getBody());

// Accessing object
        JsonObject  jsonObject = jsonobj.get("conversion_rates").getAsJsonObject();
        for(Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            System.out.println("Key = " + entry.getKey() + " Value = " + entry.getValue() );

            ratesRepository.save(new Rates(exchange,entry.getKey(),entry.getValue().toString())   );
        }

    }

    public static void createXML(String data)
    {

        String folder = Paths.get("")
                .toAbsolutePath()
                .toString();
        File file = new File( folder );
        file.mkdirs();
        try {
            BufferedWriter writer = new BufferedWriter( new FileWriter("file") );
            writer.write( data );
            writer.flush();
            writer.close();
        } catch ( IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void createXMLReport()
    {

    }
}
