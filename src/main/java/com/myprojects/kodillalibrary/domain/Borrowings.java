package com.myprojects.kodillalibrary.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="Borrowings")
public class Borrowings {

    @Id
    @GeneratedValue
    @Column(name="borrowingId")
    private Long id;

    @Column(name="borrowingDate")
    private LocalDateTime borrowingDate;

    @Column(name="returningBookDate")
    private LocalDateTime returningDate;

    @OneToOne(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "bookId")
    private Books book;

    @ManyToOne
    @JoinColumn(name = "readerId")
    private Readers reader;
}