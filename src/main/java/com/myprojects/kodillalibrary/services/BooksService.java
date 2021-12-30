package com.myprojects.kodillalibrary.services;

import com.myprojects.kodillalibrary.controllers.exceptions.BookAlreadyExistsException;
import com.myprojects.kodillalibrary.controllers.exceptions.BookNotFoundException;
import com.myprojects.kodillalibrary.domain.Books;
import com.myprojects.kodillalibrary.repositories.BooksRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BooksService {

    private final BooksRepository booksRepository;

    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Books> getAll() {
        return booksRepository.findAll().stream()
                .filter(b -> !b.isDeleted())
                .collect(Collectors.toList());
    }

    public Books getOne(final Long id) throws BookNotFoundException {
        return booksRepository.findById(id)
                .filter(t -> !t.isDeleted())
                .orElseThrow(BookNotFoundException::new);
    }

    public Books save(final Books book) throws BookAlreadyExistsException {
        Long id = book.getId();
        if (id != null && booksRepository.existsById(id)) {
            throw new BookAlreadyExistsException();
        }
        return booksRepository.save(book);
    }

    public Books update(final Books book) throws BookNotFoundException {
        Long id = book.getId();
        if (id == null || !booksRepository.existsById(id)) {
            throw new BookNotFoundException();
        }
        return booksRepository.save(book);
    }

    public void delete(final Long id) throws BookNotFoundException {
        Books book = booksRepository.findById(id)
                .orElseThrow(BookNotFoundException::new);
        book.setDeleted(true);
        booksRepository.save(book);
    }
}