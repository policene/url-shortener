package com.policene.url_shortener.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.policene.url_shortener.exceptions.ErrorResponse;

import java.time.Instant;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchElementException (NoSuchElementException exception) {
        ErrorResponse error = new ErrorResponse (
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage(),
                Instant.now()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException (MethodArgumentNotValidException exception) {

        StringBuilder builder = new StringBuilder();

        exception.getBindingResult().getFieldErrors().forEach(error ->
            builder.append(error.getDefaultMessage())
        );

        ErrorResponse error = new ErrorResponse (
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                HttpStatus.BAD_REQUEST.value(),
                builder.toString(),
                Instant.now()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExpiredLinkException.class)
    public ResponseEntity<Void> handleExpiredLinkException (ExpiredLinkException exception) {
        return new ResponseEntity<>(HttpStatus.GONE);
    }

}
