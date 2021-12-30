package com.myprojects.kodillalibrary.controllers;

import com.myprojects.kodillalibrary.controllers.exceptions.BorrowingAlreadyExistsException;
import com.myprojects.kodillalibrary.controllers.exceptions.BorrowingNotFoundException;
import com.myprojects.kodillalibrary.domain.Borrowings;
import com.myprojects.kodillalibrary.domain.dtos.BorrowingsDto;
import com.myprojects.kodillalibrary.mappers.BorrowingsMapper;
import com.myprojects.kodillalibrary.services.BooksService;
import com.myprojects.kodillalibrary.services.BorrowingsService;
import com.myprojects.kodillalibrary.services.ReadersService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("v1/library/borrowings")
public class BorrowingsController {

    private final BorrowingsService borrowingsService;
    private final BorrowingsMapper borrowingsMapper;
    private final BooksService booksService;
    private final ReadersService readersService;

    public BorrowingsController(BorrowingsService borrowingsService, BorrowingsMapper borrowingsMapper,
                                BooksService booksService, ReadersService readersService) {
        this.borrowingsService = borrowingsService;
        this.borrowingsMapper = borrowingsMapper;
        this.booksService = booksService;
        this.readersService = readersService;
    }

    @GetMapping
    public List<BorrowingsDto> getBorrowings() {
        return borrowingsMapper.mapToBorrowingsDtoList(borrowingsService.getAll());
    }

    @GetMapping("/{id}")
    public BorrowingsDto getBorrowing(@PathVariable("id") Long id) throws BorrowingNotFoundException {
        return borrowingsMapper.mapToBorrowingDto(borrowingsService.getOne(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public BorrowingsDto createBorrowing(@RequestBody BorrowingsDto borrowingDto)
            throws BorrowingAlreadyExistsException {
        Borrowings borrowing = borrowingsMapper.mapToBorrowing(borrowingDto);
            borrowing.setBorrowingDate(LocalDateTime.now());
            borrowing.setBook(booksService.getOne(borrowingDto.getBookId()));
            borrowing.setReader(readersService.getOne(borrowingDto.getReaderId()));
            Borrowings createdBorrowing = borrowingsService.save(borrowing);
        return borrowingsMapper.mapToBorrowingDto(createdBorrowing);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/{id}")
    public BorrowingsDto updateBorrowing(@PathVariable ("id") Long id,
                                      @RequestBody BorrowingsDto borrowingDto)
            throws BorrowingNotFoundException {
        Borrowings updatedBorrowing = borrowingsMapper.mapToBorrowing(borrowingDto);
            updatedBorrowing.setId(id);
            updatedBorrowing.setBorrowingDate(borrowingDto.getBorrowingDate());
            updatedBorrowing.setReturningDate(borrowingDto.getReturningDate());
            updatedBorrowing.setBook(booksService.getOne(borrowingDto.getBookId()));
            updatedBorrowing.setReader(readersService.getOne(borrowingDto.getReaderId()));
        updatedBorrowing = borrowingsService.update(updatedBorrowing);
        return borrowingsMapper.mapToBorrowingDto(updatedBorrowing);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteBorrowing(@PathVariable("id") Long id) throws BorrowingNotFoundException {
        borrowingsService.delete(id);
    }
}