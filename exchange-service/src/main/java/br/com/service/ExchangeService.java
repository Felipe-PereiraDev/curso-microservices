package br.com.service;

import br.com.repository.ExchangeRepository;
import br.com.environment.InstanceInformationService;
import br.com.model.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@Service
public class ExchangeService {

    @Autowired
    private ExchangeRepository repository;

    @Autowired
    private InstanceInformationService informationService;

    public Exchange convertTo(String from, String to, BigDecimal amount) {
        Exchange exchange = repository.getExchange(from, to).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        exchange.setEnvironment("PORT  " + informationService.retrieveServerPort());
        exchange.setConvertedValue(exchange.getConversionFactor().multiply(amount));
        return exchange;
    }
}
