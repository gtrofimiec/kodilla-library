package com.myprojects.kodillalibrary.domain;

import com.myprojects.kodillalibrary.repositories.BooksTitlesRepository;
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

    @Test
    public void shouldFindAllTitles() {

        // Given
        BooksTitles title1 = new BooksTitles();
        BooksTitles title2 = new BooksTitles();

        // When
        booksTitlesRepository.save(title1);
        booksTitlesRepository.save(title2);

        // Then
        assertEquals(2, booksTitlesRepository.findAll().size());

        // Clean Up
        booksTitlesRepository.deleteById(title1.getId());
        booksTitlesRepository.deleteById(title2.getId());
    }

    @Test
    public void shouldFindTitleById() {

        // Given
        BooksTitles title1 = new BooksTitles();
        BooksTitles title2 = new BooksTitles();

        // When
        booksTitlesRepository.save(title1);
        booksTitlesRepository.save(title2);
        Long id = title1.getId();
        Optional<BooksTitles> foundTitle = booksTitlesRepository.findById(id);

        // Then
        assertNotNull(foundTitle);
        assertEquals(id, foundTitle.get().getId());

        // Clean Up
        booksTitlesRepository.deleteById(title1.getId());
        booksTitlesRepository.deleteById(title2.getId());
    }

    @Test
    public void shouldSaveTitle() {

        // Given
        BooksTitles title1 = new BooksTitles();
        BooksTitles title2 = new BooksTitles();
        title1.setTitle("TestTitle1");
        title2.setTitle("TestTitle2");

        // When
        booksTitlesRepository.save(title1);
        booksTitlesRepository.save(title2);
        Long title1Id = title1.getId();
        Long title2Id = title2.getId();
        Optional<BooksTitles> savedTitle1 = booksTitlesRepository.findById(title1Id);
        Optional<BooksTitles> savedTitle2 = booksTitlesRepository.findById(title2Id);
        String sTitle1 = savedTitle1.get().getTitle();
        String sTitle2 = savedTitle2.get().getTitle();

        // Then
        assertTrue(savedTitle1.isPresent());
        assertTrue(savedTitle2.isPresent());
        assertEquals("TestTitle1", sTitle1);
        assertEquals("TestTitle2", sTitle2);

        // Clean Up
        booksTitlesRepository.deleteById(title1.getId());
        booksTitlesRepository.deleteById(title2.getId());
    }

    @Test
    public void shouldDeleteTitleById() {

        // Given
        BooksTitles title1 = new BooksTitles();
        BooksTitles title2 = new BooksTitles();
        booksTitlesRepository.save(title1);
        booksTitlesRepository.save(title2);

        // When
        Long id = title1.getId();
        booksTitlesRepository.deleteById(id);
        Optional<BooksTitles> removedTitle = booksTitlesRepository.findById(id);
        int availableTitles = booksTitlesRepository.findAll().size();

        // Then
        assertEquals(Optional.empty(), removedTitle);
        assertEquals(1, availableTitles);

        // Clean Up
        booksTitlesRepository.deleteById(title2.getId());
    }
}