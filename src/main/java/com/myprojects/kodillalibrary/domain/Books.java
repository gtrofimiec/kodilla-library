package com.myprojects.kodillalibrary.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
@Entity
@Table(name="Books")
public class Books {

    public Books(BooksTitles title) {
        this.title = title;
    }

    @Id
    @GeneratedValue
    @Column(name="bookId")
    private Long id;

    @NotNull
    @Column(name="bookStatus")
    private String bookStatus;

    @ManyToOne
    @JoinColumn(name = "titleId")
    private BooksTitles title;
}