package com.myprojects.kodillalibrary.domain.dtos;

import com.myprojects.kodillalibrary.domain.Borrowings;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ReadersDto {

    private Long id;
    private String name;
    private String surname;
    private LocalDateTime accountCreationDate;
    private List<Borrowings> borrowingsList;
}
