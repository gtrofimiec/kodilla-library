package com.myprojects.kodillalibrary.services;

import com.myprojects.kodillalibrary.controllers.exceptions.BorrowingAlreadyExistsException;
import com.myprojects.kodillalibrary.controllers.exceptions.BorrowingNotFoundException;
import com.myprojects.kodillalibrary.domain.Borrowings;
import com.myprojects.kodillalibrary.repositories.BorrowingsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowingsService {

    private final BorrowingsRepository borrowingsRepository;

    public BorrowingsService(BorrowingsRepository borrowingsRepository) {
        this.borrowingsRepository = borrowingsRepository;
    }

    public List<Borrowings> getAll() {
        return borrowingsRepository.findAll().stream()
                .filter(b -> !b.isDeleted())
                .collect(Collectors.toList());
    }

    public Borrowings getOne(final Long id) throws BorrowingNotFoundException {
        return borrowingsRepository.findById(id)
                .filter(b -> !b.isDeleted())
                .orElseThrow(BorrowingNotFoundException::new);
    }

    public Borrowings save(Borrowings borrowing) throws BorrowingAlreadyExistsException {
        Long id = borrowing.getId();
        if (id != null && borrowingsRepository.existsById(id)) {
            throw new BorrowingAlreadyExistsException();
        }
        return borrowingsRepository.save(borrowing);
    }

    public Borrowings update(final Borrowings borrowing) throws BorrowingNotFoundException {
        Long id = borrowing.getId();
        if (id == null || !borrowingsRepository.existsById(id)) {
            throw new BorrowingNotFoundException();
        }
        return borrowingsRepository.save(borrowing);
    }

    public void delete(final Long id) throws BorrowingNotFoundException {
        Borrowings borrowing = borrowingsRepository.findById(id)
                .orElseThrow(BorrowingNotFoundException::new);
        borrowing.setDeleted(true);
        borrowingsRepository.save(borrowing);
    }
}