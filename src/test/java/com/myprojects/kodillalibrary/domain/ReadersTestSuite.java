package com.myprojects.kodillalibrary.domain;

import com.myprojects.kodillalibrary.repositories.ReadersRepository;
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
public class ReadersTestSuite {

    @Autowired
    ReadersRepository readersRepository;

    @After
    public void cleanUpDataBaseAfterEachTest() {
        readersRepository.deleteAll();
    }

    @Test
    public void shouldFindAllReaders() {

        // Given
        Readers reader1 = new Readers();
        Readers reader2 = new Readers();

        // When
        readersRepository.save(reader1);
        readersRepository.save(reader2);

        // Then
        assertEquals(2, readersRepository.findAll().size());
    }

    @Test
    public void shouldFindReaderById() {

        // Given
        Readers reader1 = new Readers();
        Readers reader2 = new Readers();

        // When
        readersRepository.save(reader1);
        readersRepository.save(reader2);
        Long id = reader1.getId();
        Optional<Readers> foundReader = readersRepository.findById(id);

        // Then
        assertNotNull(foundReader);
        assertEquals(id, foundReader.get().getId());
    }

    @Test
    public void shouldSaveReader() {

        // Given
        Readers reader1 = new Readers();
        Readers reader2 = new Readers();
        reader1.setName("John");
        reader2.setName("William");

        // When
        readersRepository.save(reader1);
        readersRepository.save(reader2);
        Long reader1Id = reader1.getId();
        Long reader2Id = reader2.getId();
        Optional<Readers> savedReader1 = readersRepository.findById(reader1Id);
        Optional<Readers> savedReader2 = readersRepository.findById(reader2Id);
        String reader1Name = savedReader1.get().getName();
        String reader2Name = savedReader2.get().getName();

        // Then
        assertTrue(savedReader1.isPresent());
        assertTrue(savedReader2.isPresent());
        assertEquals(reader1.getName(), reader1Name);
        assertEquals(reader2.getName(), reader2Name);
    }

    @Test
    public void shouldDeleteBorrowingById() {

        // Given
        Readers reader1 = new Readers();
        Readers reader2 = new Readers();
        readersRepository.save(reader1);
        readersRepository.save(reader2);

        // When
        Long id = reader1.getId();
        readersRepository.deleteById(id);
        Optional<Readers> removedReader = readersRepository.findById(id);
        int availableReaders = readersRepository.findAll().size();

        // Then
        assertEquals(Optional.empty(), removedReader);
        assertEquals(1, availableReaders);
    }
}