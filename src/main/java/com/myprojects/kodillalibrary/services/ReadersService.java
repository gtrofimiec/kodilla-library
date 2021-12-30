package com.myprojects.kodillalibrary.services;

import com.myprojects.kodillalibrary.controllers.exceptions.ReaderAlreadyExistsException;
import com.myprojects.kodillalibrary.controllers.exceptions.ReaderNotFoundException;
import com.myprojects.kodillalibrary.domain.Readers;
import com.myprojects.kodillalibrary.repositories.ReadersRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReadersService {

    private final ReadersRepository readersRepository;

    public ReadersService(ReadersRepository readersRepository) {
        this.readersRepository = readersRepository;
    }

    public List<Readers> getAll() {
        return readersRepository.findAll().stream()
                .filter(r -> !r.isDeleted())
                .collect(Collectors.toList());
    }

    public Readers getOne(final Long id) throws ReaderNotFoundException {
        return readersRepository.findById(id)
                .filter(r -> !r.isDeleted())
                .orElseThrow(ReaderNotFoundException::new);
    }

    public Readers save(final Readers reader) throws ReaderAlreadyExistsException {
        Long id = reader.getId();
        if (id != null && readersRepository.existsById(id)) {
            throw new ReaderAlreadyExistsException();
        }
        return readersRepository.save(reader);
    }

    public Readers update(final Readers reader) throws ReaderNotFoundException {
        Long id = reader.getId();
        if (id == null || !readersRepository.existsById(id)) {
            throw new ReaderNotFoundException();
        }
        return readersRepository.save(reader);
    }

    public void delete(final Long id) throws ReaderNotFoundException {
        Readers reader = readersRepository.findById(id)
                .orElseThrow(ReaderNotFoundException::new);
        reader.setDeleted(true);
        readersRepository.save(reader);
    }
}