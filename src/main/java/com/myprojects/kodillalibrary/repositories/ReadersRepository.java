package com.myprojects.kodillalibrary.repositories;

import com.myprojects.kodillalibrary.domain.Readers;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface ReadersRepository extends CrudRepository<Readers, Long> {

    @Override
    List<Readers> findAll();

    @Override
    Optional<Readers> findById(Long readerId);

    @Override
    Readers save(Readers reader);

    @Override
    void deleteById(Long readerId);
}
