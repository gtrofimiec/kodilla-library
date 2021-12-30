package com.myprojects.kodillalibrary.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Readers")
public class Readers {

    public Readers(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "readerId")
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "surname")
    private String surname;

    @NotNull
    @Column(name = "accountCreationDate")
    private LocalDateTime accountCreationDate;

    @Column(name = "deleted")
    private boolean deleted = false;

    @OneToMany(targetEntity = Borrowings.class,
            mappedBy = "reader",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Borrowings> borrowingsList;
}