package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.ALREADY_REPORTED)
public class ResourcesAlreadyExistsException extends RuntimeException {
    public ResourcesAlreadyExistsException() {
        super();
    }

    public ResourcesAlreadyExistsException(String message) {
        super(message);
    }
}
