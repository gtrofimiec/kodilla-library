package com.myprojects.kodillalibrary.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="BooksTitles")
public class BooksTitles {

    public BooksTitles(String title, String author, int publicationYear) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    }

    @Id
    @GeneratedValue
    @NotNull
    @Column(name="titleId")
    private Long id;

    @NotNull
    @Column(name="title")
    private String title;

    @NotNull
    @Column(name="author")
    private String author;

    @NotNull
    @Column(name="publicationYear")
    private int publicationYear;

    @Column(name = "deleted")
    private boolean deleted = false;

    @OneToMany(targetEntity = Books.class,
            mappedBy = "bookTitle",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Books> booksList;
}