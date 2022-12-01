# Getting Started

### 0. Step

Swagger Test UI 
http://localhost:8081/swagger-ui/index.html

### 1. Step
   This is the sample project to get ExchangeRates from ThirdParty system

    The database created automatically and we have to call firstly those URL to fill database

   //URL

   http://localhost:8081/api/rates/exchange/EUR

   http://localhost:8081/api/rates/exchange/USD

   http://localhost:8081/api/rates/exchange/GBP

   http://localhost:8081/api/rates/exchange/CHF


### 2. Step
    Searching rates with below URL 

    //URL
    http://localhost:8081/api/rates/findExchange/USD/EUR

### 3. Step

    Generating XML File olutput with below URL
    We can developt an other small job to save this output to File 
    and also we can give a date parameter for daily reporting etc..

    //URL
    http://localhost:8081/api/rates/createXmlFile/USD,GBP,EUR,CHF
