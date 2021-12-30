package com.myprojects.kodillalibrary.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BorrowingNotFoundException extends ResponseStatusException {

    public BorrowingNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Borrowing not found");
    }
}
