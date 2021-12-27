package com.myprojects.kodillalibrary.repositories;

import com.myprojects.kodillalibrary.domain.Books;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface BooksRepository extends CrudRepository<Books, Long> {

    @Override
    List<Books> findAll();

    @Override
    Optional<Books> findById(Long bookId);

    @Override
    Books save (Books book);

    @Override
    void deleteById(Long bookId);
}