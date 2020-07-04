package com.shop.advance.academy.yordan.petrov.git.shop.rest.controllers.exeptions;

import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.ErrorResponse;
import com.shop.advance.academy.yordan.petrov.git.shop.exeption.EntityNotFoundException;
import com.shop.advance.academy.yordan.petrov.git.shop.exeption.InvalidEntityException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import java.nio.file.AccessDeniedException;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

@ControllerAdvice("com.shop.advance.academy.yordan.petrov.git.shop.rest.controllers")
@Slf4j
public class ExceptionHandlerController {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handle(EntityNotFoundException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(ex.getClass().getSimpleName(), ex.getMessage()));
    }


    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handle(HttpRequestMethodNotSupportedException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(ex.getClass().getSimpleName(), ex.getMessage()));
    }


    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handle(AccessDeniedException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ex.getMessage());
    }


    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleGenericException(final Exception ex) {

        log.error(String.format("Uncaught error happens. Stack trace is: %s", ex + getFullStackTraceLog(ex)));

        ErrorResponse errorResponse = new ErrorResponse(ex.getClass().getSimpleName(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    private String getFullStackTraceLog(Exception ex) {
        return Arrays.stream(ex.getStackTrace())
                .map(Objects::toString)
                .collect(Collectors.joining("\n"));
    }

    @ExceptionHandler({InvalidEntityException.class,
            ConstraintViolationException.class,
            HttpMessageConversionException.class,
            MethodArgumentTypeMismatchException.class
    })

    public ResponseEntity<ErrorResponse> handle(Exception ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(ex.getClass().getSimpleName(), ex.getMessage()));
    }

}
