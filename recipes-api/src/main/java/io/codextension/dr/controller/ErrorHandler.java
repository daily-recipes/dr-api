package io.codextension.dr.controller;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<NoHandlerFoundException> handle(NoHandlerFoundException e) {
        return new ResponseEntity<>(NOT_FOUND);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    protected ResponseEntity<HttpClientErrorException> handle(HttpClientErrorException e) {
        return new ResponseEntity<>(NOT_FOUND);
    }

    @ExceptionHandler({ HttpRequestMethodNotSupportedException.class })
    protected ResponseEntity<HttpRequestMethodNotSupportedException> handle(HttpRequestMethodNotSupportedException e) {
        return new ResponseEntity<>(METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
    protected ResponseEntity<MethodArgumentTypeMismatchException> handle(MethodArgumentTypeMismatchException e) {
        return new ResponseEntity<>(BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Exception> handle(Exception e) {
        log.error("Responding with HTTP Status Code 500 - Internal Server Error!", e);
        return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
    }

}