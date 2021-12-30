package com.myprojects.kodillalibrary.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BookTitleNotFoundException extends ResponseStatusException {

    public BookTitleNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Title not found");
    }
}
