package com.myprojects.kodillalibrary.mappers;

import com.myprojects.kodillalibrary.domain.Books;
import com.myprojects.kodillalibrary.domain.dtos.BooksDto;
import org.springframework.stereotype.Service;

@Service
public class BooksMapper {

    public Books mapToBook(BooksDto booksDto) {
        Books book = new Books();
            book.setId(booksDto.getId());
            book.setBookStatus(booksDto.getBookStatus());
            book.setTitle(booksDto.getTitle());
        return book;
    }

    public BooksDto mapToBookDto(Books books) {
        BooksDto bookDto = new BooksDto();
            bookDto.setId(books.getId());
            bookDto.setBookStatus(books.getBookStatus());
            bookDto.setTitle(books.getTitle());
        return bookDto;
    }
}