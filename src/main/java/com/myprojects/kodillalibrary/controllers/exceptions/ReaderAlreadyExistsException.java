package com.myprojects.kodillalibrary.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ReaderAlreadyExistsException extends ResponseStatusException {

    public ReaderAlreadyExistsException() {
        super(HttpStatus.CONFLICT, "Reader already exist");
    }
}
