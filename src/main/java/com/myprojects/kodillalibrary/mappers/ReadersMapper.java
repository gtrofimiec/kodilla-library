package com.myprojects.kodillalibrary.mappers;

import com.myprojects.kodillalibrary.domain.Readers;
import com.myprojects.kodillalibrary.domain.dtos.ReadersDto;
import org.springframework.stereotype.Service;

@Service
public class ReadersMapper {

    public Readers mapToReader(ReadersDto readerDto) {
        Readers reader = new Readers();
            reader.setId(readerDto.getId());
            reader.setName(readerDto.getName());
            reader.setSurname(readerDto.getSurname());
            reader.setAccountCreationDate(readerDto.getAccountCreationDate());
            reader.setBorrowingsList(readerDto.getBorrowingsList());
        return reader;
    }

    public ReadersDto mapToReaderDto(Readers reader) {
        ReadersDto readerDto = new ReadersDto();
            readerDto.setId(reader.getId());
            readerDto.setName(reader.getName());
            readerDto.setSurname(reader.getSurname());
            readerDto.setAccountCreationDate(reader.getAccountCreationDate());
            readerDto.setBorrowingsList(reader.getBorrowingsList());
        return readerDto;
    }
}