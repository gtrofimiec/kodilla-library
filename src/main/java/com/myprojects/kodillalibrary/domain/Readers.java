package com.myprojects.kodillalibrary.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name="Readers")
public class Readers {

    @Id
    @GeneratedValue
    @Column(name="readerId")
    private Long id;

    @NotNull
    @Column(name="name")
    private String name;

    @NotNull
    @Column(name="surname")
    private String surname;

    @NotNull
    @Column(name="accountCreationDate")
    private LocalDateTime accountCreationDate;

    @OneToMany(targetEntity = Borrowings.class,
            mappedBy = "reader",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Borrowings> borrowingsList;
}