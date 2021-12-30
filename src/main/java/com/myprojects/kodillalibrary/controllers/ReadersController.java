package com.myprojects.kodillalibrary.controllers;

import com.myprojects.kodillalibrary.controllers.exceptions.ReaderNotFoundException;
import com.myprojects.kodillalibrary.domain.Readers;
import com.myprojects.kodillalibrary.domain.dtos.ReadersDto;
import com.myprojects.kodillalibrary.mappers.ReadersMapper;
import com.myprojects.kodillalibrary.services.ReadersService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("v1/library/readers")
public class ReadersController {

    private final ReadersService readersService;
    private final ReadersMapper readersMapper;

    public ReadersController(ReadersService readersService, ReadersMapper readersMapper) {
        this.readersService = readersService;
        this.readersMapper = readersMapper;
    }

    @GetMapping
    public List<ReadersDto> getReaders() {
        return readersMapper.mapToReadersDtoList(readersService.getAll());
    }

    @GetMapping("/{id}")
    public ReadersDto getReader(@PathVariable("id") Long id) throws ReaderNotFoundException {
        return readersMapper.mapToReaderDto(readersService.getOne(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ReadersDto createReader(@RequestBody ReadersDto readerDto) throws ReaderNotFoundException {
        Readers reader = readersMapper.mapToReader(readerDto);
        reader.setAccountCreationDate(LocalDateTime.now());
        Readers createdReader = readersService.save(reader);
        return readersMapper.mapToReaderDto(createdReader);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/{id}")
    public ReadersDto updateReader(@PathVariable ("id") Long id,
                                      @RequestBody ReadersDto readerDto)
            throws ReaderNotFoundException {
        Readers updatedReader = readersMapper.mapToReader(readerDto);
        updatedReader.setId(id);
        updatedReader = readersService.update(updatedReader);
        return readersMapper.mapToReaderDto(updatedReader);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteReader(@PathVariable("id") Long id) throws ReaderNotFoundException {
        readersService.delete(id);
    }
}