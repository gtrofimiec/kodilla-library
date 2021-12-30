package com.myprojects.kodillalibrary.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BooksDto {

    private Long id;
    private String title;
    private Long titleId;
    private String bookStatus;
}