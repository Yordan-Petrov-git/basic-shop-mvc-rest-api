package com.shop.advance.academy.yordan.petrov.git.shop.web.rest.controllers.exeptions;

import com.shop.advance.academy.yordan.petrov.git.shop.data.model.utils.error.ErrorResponse;
import com.shop.advance.academy.yordan.petrov.git.shop.exeption.EntityNotFoundException;
import com.shop.advance.academy.yordan.petrov.git.shop.exeption.IllegalCardTransactionOperation;
import com.shop.advance.academy.yordan.petrov.git.shop.exeption.IllegalDeleteOperation;
import com.shop.advance.academy.yordan.petrov.git.shop.exeption.InvalidEntityException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import java.nio.file.AccessDeniedException;

@ControllerAdvice("com.shop.advance.academy.yordan.petrov.git.shop.web.rest.controllers")
@Slf4j
public class ExceptionHandlerController implements ErrorController {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handle(EntityNotFoundException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(ex.getClass().getSimpleName(), ex.getMessage()));
    }

    @ExceptionHandler(IllegalCardTransactionOperation.class)
    public ResponseEntity<ErrorResponse> handle(IllegalCardTransactionOperation ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(ex.getClass().getSimpleName(), ex.getMessage()));
    }


    @ExceptionHandler(IllegalDeleteOperation.class)
    public ResponseEntity<ErrorResponse> handle(IllegalDeleteOperation ex) {
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

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<String> handle(UsernameNotFoundException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ex.getMessage());
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

    @Override
    public String getErrorPath() {
        return null;
    }
}
