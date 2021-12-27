package com.myprojects.kodillalibrary.domain;

import com.myprojects.kodillalibrary.repositories.BooksRepository;
import com.myprojects.kodillalibrary.repositories.BooksTitlesRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BooksTestSuite {

    @Autowired
    BooksRepository booksRepository;

    @Autowired
    BooksTitlesRepository booksTitlesRepository;

    @After
    public void cleanUpDataBaseAfterEachTest() {
        booksTitlesRepository.deleteAll();
        booksRepository.deleteAll();
    }

    @Test
    public void shouldFindAllBooks() {

        // Given
        Books book1 = new Books();
        Books book2 = new Books();

        // When
        booksRepository.save(book1);
        booksRepository.save(book2);

        // Then
        assertEquals(2, booksRepository.findAll().size());
    }

    @Test
    public void shouldFindBookById() {

        // Given
        Books book1 = new Books();
        Books book2 = new Books();

        // When
        booksRepository.save(book1);
        booksRepository.save(book2);
        Long id = book1.getId();
        Optional<Books> foundBook = booksRepository.findById(id);

        // Then
        assertNotNull(foundBook);
        assertEquals(id, foundBook.get().getId());
    }

    @Test
    public void shouldSaveBook() {

        // Given
        Books book1 = new Books();
        Books book2 = new Books();
        book1.setBookStatus("destroyed");
        book2.setBookStatus("borrowed");

        // When
        booksRepository.save(book1);
        booksRepository.save(book2);
        Long book1Id = book1.getId();
        Long book2Id = book2.getId();
        Optional<Books> savedBook1 = booksRepository.findById(book1Id);
        Optional<Books> savedBook2 = booksRepository.findById(book2Id);
        String book1Status = savedBook1.get().getBookStatus();
        String book2Status = savedBook2.get().getBookStatus();

        // Then
        assertTrue(savedBook1.isPresent());
        assertTrue(savedBook2.isPresent());
        assertEquals("destroyed", book1Status);
        assertEquals("borrowed", book2Status);
    }

    @Test
    public void shouldDeleteBookById(){

        // Given
        Books book1 = new Books();
        Books book2 = new Books();
        booksRepository.save(book1);
        booksRepository.save(book2);

        // When
        Long id = book1.getId();
        booksRepository.deleteById(id);
        Optional<Books> removedBook = booksRepository.findById(id);
        int availableBooks = booksRepository.findAll().size();

        // Then
        assertEquals(Optional.empty(), removedBook);
        assertEquals(1, availableBooks);
    }
}