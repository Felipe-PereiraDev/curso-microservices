package br.com.client;

import br.com.dto.Exchange;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

//@Component
public class ApiRestClient implements ExchangeApiClient{

    @Override
    public Exchange getExchange(BigDecimal amount, String from, String to) {
        try {
            String URL = "http://localhost:8000/exchange-service";
            Map<String, String> params = new HashMap<>();
            params.put("amount", amount.toString());
            params.put("from", from);
            params.put("to", to);

            ResponseEntity<Exchange> forEntity = new RestTemplate()
                    .getForEntity(URL + "/{amount}/{from}/{to}", Exchange.class, params);

            return forEntity.getBody();
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "ERRO AO CONVERTER PREÇO DO LIVRO");
        }
    }
}
