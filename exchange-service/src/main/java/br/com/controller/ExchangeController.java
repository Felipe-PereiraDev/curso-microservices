package br.com.controller;


import br.com.model.Exchange;
import br.com.service.ExchangeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping(value = "/exchange-service")
public class ExchangeController {
    @Autowired
    private ExchangeService exchangeService;
    private static Logger logger = LoggerFactory.getLogger(ExchangeController.class);
    // localhost:8000/exchange-service

    @GetMapping(value = "/{amount}/{from}/{to}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Exchange> getExchange(@PathVariable("from") String from,
                                               @PathVariable("to") String to,
                                               @PathVariable("amount") BigDecimal amount) {
        logger.info("getExchange is called with -> {},  {} and {}", amount, from, to);
        Exchange exchange = exchangeService.convertTo(from, to, amount);
        return ResponseEntity.ok().body(exchange);
    }
}
