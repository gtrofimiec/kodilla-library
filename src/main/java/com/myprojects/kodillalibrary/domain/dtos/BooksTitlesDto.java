package com.myprojects.kodillalibrary.domain.dtos;

import com.myprojects.kodillalibrary.domain.Books;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BooksTitlesDto {

    public BooksTitlesDto(Long id, String title, String author, int publicationYear) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    }

    private Long id;
    private String title;
    private String author;
    private int publicationYear;
    private List<Books> booksList;
}