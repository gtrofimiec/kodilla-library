package com.myprojects.kodillalibrary.domain.dtos;

import com.myprojects.kodillalibrary.domain.Books;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class BooksTitlesDto {

    private Long id;
    private String title;
    private String author;
    private LocalDate publicationYear;
    private List<Books> booksList;
}
