package com.myprojects.kodillalibrary.domain.dtos;

import com.myprojects.kodillalibrary.domain.Books;
import com.myprojects.kodillalibrary.domain.Readers;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BorrowingsDto {

    private Long id;
    private LocalDateTime borrowingDate;
    private LocalDateTime returningDate;
    private Books book;
    private Readers reader;
}
