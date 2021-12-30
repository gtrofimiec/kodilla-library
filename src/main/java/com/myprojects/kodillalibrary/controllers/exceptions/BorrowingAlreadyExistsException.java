package com.myprojects.kodillalibrary.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BorrowingAlreadyExistsException extends ResponseStatusException {

    public BorrowingAlreadyExistsException() {
        super(HttpStatus.CONFLICT, "Borrowing already exist");
    }
}
