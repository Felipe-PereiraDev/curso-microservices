package br.com.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "book")
@Table(name = "book")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "launch_date", nullable = false)
//    @Temporal(TemporalType.DATE)
    private LocalDateTime launchDate;

    @Column(name = "price", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "#0.00")
    private BigDecimal price;

    @Column(name = "title", nullable = false)
    private String title;

    @Transient
    private String currency;

    @Transient
    private String environment;
}
