package com.myprojects.kodillalibrary.controllers;

import com.myprojects.kodillalibrary.controllers.exceptions.BookNotFoundException;
import com.myprojects.kodillalibrary.controllers.exceptions.BookTitleAlreadyExistsException;
import com.myprojects.kodillalibrary.controllers.exceptions.BookTitleNotFoundException;
import com.myprojects.kodillalibrary.domain.BooksTitles;
import com.myprojects.kodillalibrary.domain.dtos.BooksDto;
import com.myprojects.kodillalibrary.domain.dtos.BooksTitlesDto;
import com.myprojects.kodillalibrary.mappers.BooksMapper;
import com.myprojects.kodillalibrary.mappers.BooksTitlesMapper;
import com.myprojects.kodillalibrary.services.BooksService;
import com.myprojects.kodillalibrary.services.BooksTitlesService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/library/titles")
public class BookTitlesController {

    private final BooksTitlesService booksTitlesService;
    private final BooksTitlesMapper booksTitlesMapper;
    private final BooksService booksService;
    private final BooksMapper booksMapper;

    public BookTitlesController(BooksTitlesService booksTitlesService, BooksTitlesMapper booksTitlesMapper,
                                BooksService booksService, BooksMapper booksMapper) {
        this.booksTitlesService = booksTitlesService;
        this.booksTitlesMapper = booksTitlesMapper;
        this.booksService = booksService;
        this.booksMapper = booksMapper;
    }

    @GetMapping
    public List<BooksTitlesDto> getTitles() {
        return booksTitlesMapper.mapToTitlesDtoList(booksTitlesService.getAll());
    }

    @GetMapping("/{id}")
    public BooksTitlesDto getTitle(@PathVariable("id") Long id) throws BookTitleNotFoundException {
        return booksTitlesMapper.mapToTitleDto(booksTitlesService.getOne(id));
    }

    @GetMapping("/{id}/{status}")
    public String getBooksAvailable(
            @PathVariable("id") Long id,
            @PathVariable("status") String status)
            throws BookNotFoundException, BookTitleNotFoundException {
        List<BooksDto> booksList = booksMapper.mapToBooksDtoList(booksService.getAll().stream()
                .filter(b -> b.getBookTitle().equals(booksTitlesService.getOne(id)))
                .filter(b -> Objects.equals(b.getBookStatus(), status))
                .collect(Collectors.toList()));
        return "Found " + booksList.size()
                + " books with title: " + booksTitlesService.getOne(id).getTitle()
                + " and status: " + status;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public BooksTitlesDto createTitle(@RequestBody BooksTitlesDto titleDto) throws BookTitleAlreadyExistsException {
        BooksTitles title = booksTitlesMapper.mapToTitle(titleDto);
        BooksTitles createdTitle = booksTitlesService.save(title);
        return booksTitlesMapper.mapToTitleDto(createdTitle);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/{id}")
    public BooksTitlesDto updateTitle(@PathVariable ("id") Long id,
                                      @RequestBody BooksTitlesDto titleDto)
            throws BookTitleNotFoundException {
        BooksTitles updatedTitle = booksTitlesMapper.mapToTitle(titleDto);
        updatedTitle.setId(id);
        updatedTitle = booksTitlesService.update(updatedTitle);
        return booksTitlesMapper.mapToTitleDto(updatedTitle);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteTitle(@PathVariable("id") Long id) throws BookTitleNotFoundException {
        booksTitlesService.delete(id);
    }
}