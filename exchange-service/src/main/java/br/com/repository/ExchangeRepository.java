package br.com.repository;

import br.com.model.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ExchangeRepository extends JpaRepository<Exchange, Long> {

    @Query(value = "SELECT * FROM exchange u WHERE UPPER(from_currency) = UPPER(:from) AND UPPER(to_currency) = UPPER(:to)", nativeQuery = true)
    Optional<Exchange> getExchange(@Param("from") String from, @Param("to") String to);
}
