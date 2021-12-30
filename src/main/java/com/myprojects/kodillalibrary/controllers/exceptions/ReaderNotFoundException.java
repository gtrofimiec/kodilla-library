package com.myprojects.kodillalibrary.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ReaderNotFoundException extends ResponseStatusException {

    public ReaderNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Reader not found");
    }
}
