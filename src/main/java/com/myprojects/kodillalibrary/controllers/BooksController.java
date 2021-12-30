package com.myprojects.kodillalibrary.controllers;

import com.myprojects.kodillalibrary.controllers.exceptions.BookAlreadyExistsException;
import com.myprojects.kodillalibrary.controllers.exceptions.BookNotFoundException;
import com.myprojects.kodillalibrary.controllers.exceptions.BookTitleNotFoundException;
import com.myprojects.kodillalibrary.domain.Books;
import com.myprojects.kodillalibrary.domain.dtos.BooksDto;
import com.myprojects.kodillalibrary.mappers.BooksMapper;
import com.myprojects.kodillalibrary.services.BooksService;
import com.myprojects.kodillalibrary.services.BooksTitlesService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/library/books")
public class BooksController {

    private final BooksService booksService;
    private final BooksMapper booksMapper;
    private final BooksTitlesService booksTitlesService;

    public BooksController(BooksService booksService,
                           BooksMapper booksMapper,
                           BooksTitlesService booksTitlesService) {
        this.booksService = booksService;
        this.booksMapper = booksMapper;
        this.booksTitlesService = booksTitlesService;
    }

    @GetMapping
    public List<BooksDto> getBooks() {
        return booksMapper.mapToBooksDtoList(booksService.getAll());
    }

    @GetMapping("/{id}")
    public BooksDto getBook(@PathVariable("id") Long id) throws BookTitleNotFoundException {
        return booksMapper.mapToBookDto(booksService.getOne(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public BooksDto createBook(@RequestBody BooksDto bookDto) throws BookTitleNotFoundException,
            BookAlreadyExistsException {
        Books book = booksMapper.mapToBook(bookDto);
        String bookTitle = booksTitlesService.getOne(bookDto.getTitleId()).getTitle();
        book.getBookTitle().setTitle(bookTitle);
        booksService.save(book);
        return booksMapper.mapToBookDto(book);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/{id}")
    public BooksDto updateBook(@PathVariable("id") Long id, @RequestBody BooksDto bookDto)
            throws BookTitleNotFoundException, BookNotFoundException {
        Books updatedBook = booksMapper.mapToBook(bookDto);
        updatedBook.setId(id);
        String bookTitle = booksTitlesService.getOne(bookDto.getTitleId()).getTitle();
        updatedBook.getBookTitle().setTitle(bookTitle);
        booksService.update(updatedBook);
        return booksMapper.mapToBookDto(updatedBook);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteBook(@PathVariable("id") Long id) throws BookNotFoundException {
        booksService.delete(id);
    }
}