package com.myprojects.kodillalibrary.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="Books")
public class Books {

    public Books(String bookStatus) {
        this.bookStatus = bookStatus;
    }

    @Id
    @GeneratedValue
    @NotNull
    @Column(name="bookId")
    private Long id;

    @NotNull
    @Column(name="bookStatus")
    private String bookStatus;

    @ManyToOne
    @JoinColumn(name = "titleId")
    private BooksTitles bookTitle;

    @Column(name = "deleted")
    private boolean deleted = false;
}