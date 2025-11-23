package br.com.client;

import br.com.dto.Exchange;

import java.math.BigDecimal;

public interface ExchangeApiClient {

    Exchange getExchange(BigDecimal amount, String from, String to);
}
