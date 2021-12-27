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
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BooksTitlesTestSuite {

    @Autowired
    BooksTitlesRepository booksTitlesRepository;

    @Autowired
    BooksRepository booksRepository;

    @After
    public void cleanUpDataBaseAfterEachTest() {
        booksTitlesRepository.deleteAll();
        booksRepository.deleteAll();
    }

    @Test
    public void shouldFindAllTitles() {

        // Given
        BooksTitles bookTitle1 = new BooksTitles();
        BooksTitles bookTitle2 = new BooksTitles();

        // When
        booksTitlesRepository.save(bookTitle1);
        booksTitlesRepository.save(bookTitle2);

        // Then
        assertEquals(2, booksTitlesRepository.findAll().size());
    }

    @Test
    public void shouldFindTitleById() {

        // Given
        BooksTitles bookTitle1 = new BooksTitles();
        BooksTitles bookTitle2 = new BooksTitles();

        // When
        booksTitlesRepository.save(bookTitle1);
        booksTitlesRepository.save(bookTitle2);
        Long id = bookTitle1.getId();
        Optional<BooksTitles> foundTitle = booksTitlesRepository.findById(id);

        // Then
        assertNotNull(foundTitle);
        assertEquals(id, foundTitle.get().getId());
    }

    @Test
    public void shouldSaveTitle() {

        // Given
        BooksTitles bookTitle1 = new BooksTitles();
        BooksTitles bookTitle2 = new BooksTitles();
        bookTitle1.setTitle("TestTitle1");
        bookTitle2.setTitle("TestTitle2");

        // When
        booksTitlesRepository.save(bookTitle1);
        booksTitlesRepository.save(bookTitle2);
        Long title1Id = bookTitle1.getId();
        Long title2Id = bookTitle2.getId();
        Optional<BooksTitles> savedTitle1 = booksTitlesRepository.findById(title1Id);
        Optional<BooksTitles> savedTitle2 = booksTitlesRepository.findById(title2Id);
        String title1 = savedTitle1.get().getTitle();
        String title2 = savedTitle2.get().getTitle();

        // Then
        assertTrue(savedTitle1.isPresent());
        assertTrue(savedTitle2.isPresent());
        assertEquals("TestTitle1", title1);
        assertEquals("TestTitle2", title2);
    }

    @Test
    public void shouldDeleteTitleById() {

        // Given
        BooksTitles bookTitle1 = new BooksTitles();
        BooksTitles bookTitle2 = new BooksTitles();
        booksTitlesRepository.save(bookTitle1);
        booksTitlesRepository.save(bookTitle2);

        // When
        Long id = bookTitle1.getId();
        booksTitlesRepository.deleteById(id);
        Optional<BooksTitles> removedTitle = booksTitlesRepository.findById(id);
        int availableTitles = booksTitlesRepository.findAll().size();

        // Then
        assertEquals(Optional.empty(), removedTitle);
        assertEquals(1, availableTitles);
    }
}