package com.myprojects.kodillalibrary.mappers;

import com.myprojects.kodillalibrary.domain.Borrowings;
import com.myprojects.kodillalibrary.domain.dtos.BorrowingsDto;
import com.myprojects.kodillalibrary.services.BooksService;
import com.myprojects.kodillalibrary.services.ReadersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowingsMapper {

    @Autowired
    private BooksService booksService;
    @Autowired
    private ReadersService readersService;

    public Borrowings mapToBorrowing(BorrowingsDto borrowingDto) {
        Borrowings borrowing = new Borrowings(
                borrowingDto.getBorrowingDate(),
                borrowingDto.getReturningDate()
        );
            borrowing.setBook(booksService.getOne(borrowingDto.getBookId()));
            borrowing.setReader(readersService.getOne(borrowingDto.getReaderId()));
        return borrowing;
    }

    public BorrowingsDto mapToBorrowingDto(Borrowings borrowing) {
        BorrowingsDto borrowingDto = new BorrowingsDto(
                borrowing.getId(),
                borrowing.getBorrowingDate(),
                borrowing.getReturningDate(),
                borrowing.getBook().getId(),
                borrowing.getBook().getBookTitle().getTitle(),
                borrowing.getReader().getId()
        );
        borrowingDto.setName(borrowing.getReader().getName() + " " + borrowing.getReader().getSurname());
        return borrowingDto;
    }

    public List<BorrowingsDto> mapToBorrowingsDtoList(List<Borrowings> borrowingsList) {
        return borrowingsList.stream()
                .map(this::mapToBorrowingDto)
                .collect(Collectors.toList());
    }
}