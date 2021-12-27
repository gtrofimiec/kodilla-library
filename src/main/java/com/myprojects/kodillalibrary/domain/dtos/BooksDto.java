package com.myprojects.kodillalibrary.domain.dtos;

import com.myprojects.kodillalibrary.domain.BooksTitles;
import lombok.Data;

@Data
public class BooksDto {

    private Long id;
    private String bookStatus;
    private BooksTitles title;
}
