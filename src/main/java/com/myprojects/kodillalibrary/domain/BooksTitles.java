package com.myprojects.kodillalibrary.domain;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@Table(name="BooksTitles")
public class BooksTitles {

    public BooksTitles() {
        this.booksList = new ArrayList<>();
    }

    @Id
    @GeneratedValue
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
    private LocalDate publicationYear;

    @OneToMany(targetEntity = Books.class,
            mappedBy = "title",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Books> booksList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BooksTitles that = (BooksTitles) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}