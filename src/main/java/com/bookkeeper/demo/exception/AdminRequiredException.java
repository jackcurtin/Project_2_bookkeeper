package com.bookkeeper.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class AdminRequiredException extends RuntimeException{
    public AdminRequiredException(String message) {super(message);}
}
