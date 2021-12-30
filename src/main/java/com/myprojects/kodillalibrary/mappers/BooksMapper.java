package com.myprojects.kodillalibrary.mappers;

import com.myprojects.kodillalibrary.controllers.exceptions.BookTitleNotFoundException;
import com.myprojects.kodillalibrary.domain.Books;
import com.myprojects.kodillalibrary.domain.dtos.BooksDto;
import com.myprojects.kodillalibrary.services.BooksTitlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BooksMapper {

    @Autowired
    private BooksTitlesService booksTitlesService;

    public Books mapToBook(final BooksDto booksDto) throws BookTitleNotFoundException {
        Books book = new Books(booksDto.getBookStatus());
        book.setBookTitle(booksTitlesService.getOne(booksDto.getTitleId()));
        return book;
    }

    public BooksDto mapToBookDto(final Books books) {
        return new BooksDto(
                books.getId(),
                books.getBookTitle().getTitle(),
                books.getBookTitle().getId(),
                books.getBookStatus()
        );
    }

    public List<BooksDto> mapToBooksDtoList(final List<Books> booksList) {
        return booksList.stream()
                .map(this::mapToBookDto)
                .collect(Collectors.toList());
    }
}