package br.com.client;


import br.com.dto.Exchange;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

@Component
@FeignClient(name = "exchange-service")
public interface IFeignClient extends ExchangeApiClient{

    @Override
    @GetMapping(value = "/exchange-service/{amount}/{from}/{to}")
    Exchange getExchange(@PathVariable("amount") BigDecimal amount,
                         @PathVariable("from") String from,
                         @PathVariable("to") String to);
}
