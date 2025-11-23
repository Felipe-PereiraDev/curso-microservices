package br.com.controller;

import br.com.model.Book;
import br.com.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "book-service")
public class BookController {

    @Autowired
    private BookService bookService;


    // http://localhost:8100/book-service/1/BRL
    @GetMapping(value = "/{id}/{currency}")
    public ResponseEntity<Book> findById(@PathVariable("id") Long id,
                                         @PathVariable("currency") String currency) {
        return ResponseEntity.ok(bookService.findById(id, currency));
    }
}
