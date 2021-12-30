package com.myprojects.kodillalibrary.mappers;

import com.myprojects.kodillalibrary.domain.Readers;
import com.myprojects.kodillalibrary.domain.dtos.ReadersDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReadersMapper {

    public Readers mapToReader(ReadersDto readerDto) {
        return new Readers(
                readerDto.getName(),
                readerDto.getSurname()
//                readerDto.getAccountCreationDate()
        );
    }

    public ReadersDto mapToReaderDto(Readers reader) {
        return new ReadersDto(
                reader.getId(),
                reader.getName(),
                reader.getSurname(),
                reader.getAccountCreationDate()
        );
    }

    public List<ReadersDto> mapToReadersDtoList(List<Readers> readersList) {
        return readersList.stream()
                .map(this::mapToReaderDto)
                .collect(Collectors.toList());
    }
}