package com.myprojects.kodillalibrary.services;

import com.myprojects.kodillalibrary.controllers.exceptions.BookTitleAlreadyExistsException;
import com.myprojects.kodillalibrary.controllers.exceptions.BookTitleNotFoundException;
import com.myprojects.kodillalibrary.domain.BooksTitles;
import com.myprojects.kodillalibrary.repositories.BooksTitlesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BooksTitlesService {

    private final BooksTitlesRepository booksTitlesRepository;

    public BooksTitlesService(BooksTitlesRepository booksTitlesRepository) {
        this.booksTitlesRepository = booksTitlesRepository;
    }

    public List<BooksTitles> getAll() {
        return booksTitlesRepository.findAll().stream()
                .filter(t -> !t.isDeleted())
                .collect(Collectors.toList());
    }

    public BooksTitles getOne(final Long id) throws BookTitleNotFoundException {
        return booksTitlesRepository.findById(id)
                .filter(t -> !t.isDeleted())
                .orElseThrow(BookTitleNotFoundException::new);
    }

    public BooksTitles save(final BooksTitles title) throws BookTitleAlreadyExistsException {
        Long id = title.getId();
        if (id != null && booksTitlesRepository.existsById(id)) {
            throw new BookTitleAlreadyExistsException();
        }
        return booksTitlesRepository.save(title);
    }

    public BooksTitles update(final BooksTitles title) throws BookTitleNotFoundException {
        Long id = title.getId();
        if (id == null || !booksTitlesRepository.existsById(id)) {
            throw new BookTitleNotFoundException();
        }
        return booksTitlesRepository.save(title);
    }

    public void delete(final Long id) throws BookTitleNotFoundException {
        BooksTitles title = booksTitlesRepository.findById(id)
                .orElseThrow(BookTitleNotFoundException::new);
        title.setDeleted(true);
        booksTitlesRepository.save(title);
    }
}