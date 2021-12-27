package com.myprojects.kodillalibrary.repositories;

import com.myprojects.kodillalibrary.domain.BooksTitles;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Repository
@Transactional
public interface BooksTitlesRepository extends CrudRepository<BooksTitles, Long> {

    @Override
    List<BooksTitles> findAll();

    @Override
    Optional<BooksTitles> findById(Long titleId);

    @Override
    BooksTitles save(BooksTitles title);

    @Override
    void deleteById(Long titleId);
}
