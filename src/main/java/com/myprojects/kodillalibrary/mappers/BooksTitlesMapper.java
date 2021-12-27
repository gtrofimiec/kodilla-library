package com.myprojects.kodillalibrary.mappers;

import com.myprojects.kodillalibrary.domain.BooksTitles;
import com.myprojects.kodillalibrary.domain.dtos.BooksTitlesDto;
import org.springframework.stereotype.Service;

@Service
public class BooksTitlesMapper {

    public BooksTitles mapToTitle(BooksTitlesDto titleDto) {
        BooksTitles booksTitles = new BooksTitles();
            booksTitles.setId(titleDto.getId());
            booksTitles.setTitle(titleDto.getTitle());
            booksTitles.setAuthor(titleDto.getAuthor());
            booksTitles.setPublicationYear(titleDto.getPublicationYear());
            booksTitles.setBooksList(titleDto.getBooksList());
        return booksTitles;
    }

    public BooksTitlesDto mapToTitleDto(BooksTitles title) {
        BooksTitlesDto booksTitlesDto = new BooksTitlesDto();
            booksTitlesDto.setId(title.getId());
            booksTitlesDto.setTitle(title.getTitle());
            booksTitlesDto.setAuthor(title.getAuthor());
            booksTitlesDto.setPublicationYear(title.getPublicationYear());
            booksTitlesDto.setBooksList(title.getBooksList());
        return booksTitlesDto;
    }
}