package com.myprojects.kodillalibrary.domain;

import com.myprojects.kodillalibrary.repositories.BooksRepository;
import com.myprojects.kodillalibrary.repositories.BorrowingsRepository;
import com.myprojects.kodillalibrary.repositories.ReadersRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BorrowingsTestSuite {

    @Autowired
    BorrowingsRepository borrowingsRepository;

    @Autowired
    BooksRepository booksRepository;

    @Autowired
    ReadersRepository readersRepository;

    @After
    public void cleanUpDataBaseAfterEachTest() {
        borrowingsRepository.deleteAll();
        booksRepository.deleteAll();
        readersRepository.deleteAll();
    }

    @Test
    public void shouldFindAllBorrowings() {

        // Given
        Borrowings borrowing1 = new Borrowings();
        Borrowings borrowing2 = new Borrowings();

        // When
        borrowingsRepository.save(borrowing1);
        borrowingsRepository.save(borrowing2);

        // Then
        assertEquals(2, borrowingsRepository.findAll().size());
    }

    @Test
    public void shouldFindBorrowingById() {

        // Given
        Borrowings borrowing1 = new Borrowings();
        Borrowings borrowing2 = new Borrowings();

        // When
        borrowingsRepository.save(borrowing1);
        borrowingsRepository.save(borrowing2);
        Long id = borrowing1.getId();
        Optional<Borrowings> foundBorrowing = borrowingsRepository.findById(id);

        // Then
        assertNotNull(foundBorrowing);
        assertEquals(id, foundBorrowing.get().getId());
    }

    @Test
    public void shouldSaveBorrowing() {

        // Given
        Borrowings borrowing1 = new Borrowings();
        Borrowings borrowing2 = new Borrowings();
        borrowing1.setBorrowingDate(LocalDateTime.of(2021,12,20,13,24));
        borrowing2.setBorrowingDate(LocalDateTime.of(2021,8,12,9,11));

        // When
        borrowingsRepository.save(borrowing1);
        borrowingsRepository.save(borrowing2);
        Long borrowingId1 = borrowing1.getId();
        Long borrowingId2 = borrowing2.getId();
        Optional<Borrowings> savedBorrowing1 = borrowingsRepository.findById(borrowingId1);
        Optional<Borrowings> savedBorrowing2 = borrowingsRepository.findById(borrowingId2);
        LocalDateTime borrowingDate1 = savedBorrowing1.get().getBorrowingDate();
        LocalDateTime borrowingDate2 = savedBorrowing2.get().getBorrowingDate();

        // Then
        assertTrue(savedBorrowing1.isPresent());
        assertTrue(savedBorrowing2.isPresent());
        assertEquals(borrowing1.getBorrowingDate(), borrowingDate1);
        assertEquals(borrowing2.getBorrowingDate(), borrowingDate2);
    }

    @Test
    public void shouldDeleteBorrowingById() {

        // Given
        Borrowings borrowing1 = new Borrowings();
        Borrowings borrowing2 = new Borrowings();
        borrowingsRepository.save(borrowing1);
        borrowingsRepository.save(borrowing2);

        // When
        Long id = borrowing1.getId();
        borrowingsRepository.deleteById(id);
        Optional<Borrowings> removedBorrowing = borrowingsRepository.findById(id);
        int availableBorrowings = borrowingsRepository.findAll().size();

        // Then
        assertEquals(Optional.empty(), removedBorrowing);
        assertEquals(1, availableBorrowings);
    }
}