package com.myprojects.kodillalibrary.domain.dtos;

import com.myprojects.kodillalibrary.domain.Borrowings;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReadersDto {

    public ReadersDto(Long id, String name, String surname, LocalDateTime accountCreationDate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.accountCreationDate = accountCreationDate;
    }

    private Long id;
    private String name;
    private String surname;
    private LocalDateTime accountCreationDate;
    private List<Borrowings> borrowingsList;
}