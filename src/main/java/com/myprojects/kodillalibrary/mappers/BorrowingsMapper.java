package com.myprojects.kodillalibrary.mappers;

import com.myprojects.kodillalibrary.domain.Borrowings;
import com.myprojects.kodillalibrary.domain.dtos.BorrowingsDto;
import org.springframework.stereotype.Service;

@Service
public class BorrowingsMapper {

    public Borrowings mapToBorrowing(BorrowingsDto borrowingDto) {
        Borrowings borrowing = new Borrowings();
            borrowing.setId(borrowingDto.getId());
            borrowing.setBorrowingDate(borrowingDto.getBorrowingDate());
            borrowing.setReturningDate(borrowingDto.getReturningDate());
            borrowing.setBook(borrowingDto.getBook());
            borrowing.setReader(borrowingDto.getReader());
        return borrowing;
    }

    public BorrowingsDto mapToBorrowingDto(Borrowings borrowing) {
        BorrowingsDto borrowingDto = new BorrowingsDto();
            borrowingDto.setId(borrowing.getId());
            borrowingDto.setBorrowingDate(borrowing.getBorrowingDate());
            borrowingDto.setReturningDate(borrowing.getReturningDate());
            borrowingDto.setBook(borrowing.getBook());
            borrowingDto.setReader(borrowing.getReader());
        return borrowingDto;
    }
}