package com.myprojects.kodillalibrary.mappers;

import com.myprojects.kodillalibrary.domain.BooksTitles;
import com.myprojects.kodillalibrary.domain.dtos.BooksTitlesDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BooksTitlesMapper {

    public BooksTitles mapToTitle(BooksTitlesDto titleDto) {
        return new BooksTitles(
                titleDto.getTitle(),
                titleDto.getAuthor(),
                titleDto.getPublicationYear()
        );
    }

    public BooksTitlesDto mapToTitleDto(BooksTitles title) {
        return new BooksTitlesDto(
                title.getId(),
                title.getTitle(),
                title.getAuthor(),
                title.getPublicationYear()
        );
    }

    public List<BooksTitlesDto> mapToTitlesDtoList(List<BooksTitles> titlesList) {
        return titlesList.stream()
                .map(this::mapToTitleDto)
                .collect(Collectors.toList());
    }
}