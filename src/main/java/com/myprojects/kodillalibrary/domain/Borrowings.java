package com.myprojects.kodillalibrary.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="Borrowings")
public class Borrowings {

    public Borrowings(LocalDateTime borrowingDate, LocalDateTime returningDate) {
        this.borrowingDate = borrowingDate;
        this.returningDate = returningDate;
    }

    @Id
    @GeneratedValue
    @NotNull
    @Column(name="borrowingId")
    private Long id;

    @Column(name="borrowingDate")
    private LocalDateTime borrowingDate;

    @Column(name="returningBookDate")
    private LocalDateTime returningDate;

    @Column(name = "deleted")
    private boolean deleted = false;

    @OneToOne(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "bookId")
    private Books book;

    @ManyToOne
    @JoinColumn(name = "readerId")
    private Readers reader;
}