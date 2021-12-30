package com.myprojects.kodillalibrary.repositories;

import com.myprojects.kodillalibrary.domain.Borrowings;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowingsRepository extends CrudRepository<Borrowings, Long> {

    @Override
    List<Borrowings> findAll();

    @Override
    Optional<Borrowings> findById(Long borrowingId);

    @Override
    Borrowings save(Borrowings borrowing);

    @Override
    void deleteById(Long borrowingId);
}