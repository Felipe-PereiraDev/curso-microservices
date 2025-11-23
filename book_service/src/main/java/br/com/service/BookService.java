package br.com.service;

import br.com.client.ExchangeApiClient;
import br.com.dto.Exchange;
import br.com.environment.InstanceInformationService;
import br.com.model.Book;
import br.com.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.RoundingMode;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private InstanceInformationService informationService;

    @Autowired
    private ExchangeApiClient exchangeApiClient;


    public Book findById(Long id, String currency) {
        String port = informationService.retrieveServerPort();
        var book = bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Exchange exchange = exchangeApiClient.getExchange(book.getPrice(), "USD", currency);

        book.setPrice(exchange.getConvertedValue().setScale(2, RoundingMode.HALF_UP));
        book.setCurrency(currency.toUpperCase());
        book.setEnvironment("BOOK PORT: " + port + " EXCHANGE PORT: " + exchange.getEnvironment());
        return book;
    }

}
