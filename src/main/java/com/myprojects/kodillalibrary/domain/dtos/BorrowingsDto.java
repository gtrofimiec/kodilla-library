package com.myprojects.kodillalibrary.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BorrowingsDto {

    public BorrowingsDto(Long id, LocalDateTime borrowingDate, LocalDateTime returningDate,
                         Long bookId, String title, Long readerId) {
        this.id = id;
        this.borrowingDate = borrowingDate;
        this.returningDate = returningDate;
        this.bookId = bookId;
        this.title = title;
        this.readerId = readerId;
    }

    private Long id;
    private LocalDateTime borrowingDate;
    private LocalDateTime returningDate;
    private Long bookId;
    private String title;
    private Long readerId;
    private String name;
}