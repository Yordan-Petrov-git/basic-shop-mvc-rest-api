package com.shop.advance.academy.yordan.petrov.git.shop.web.rest.controllers.exeptions;

import com.shop.advance.academy.yordan.petrov.git.shop.data.model.utils.error.ErrorResponse;
import com.shop.advance.academy.yordan.petrov.git.shop.exeption.EntityNotFoundException;
import com.shop.advance.academy.yordan.petrov.git.shop.exeption.IllegalCardTransactionOperation;
import com.shop.advance.academy.yordan.petrov.git.shop.exeption.IllegalDeleteOperation;
import com.shop.advance.academy.yordan.petrov.git.shop.exeption.InvalidEntityException;
import lombok.extern.slf4j.Slf4j;
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

/**
 * Class handling globall exceptions.
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
@ControllerAdvice("com.shop.advance.academy.yordan.petrov.git.shop.web.rest.controllers")
@Slf4j
public class ExceptionHandlerController {

    /**
     * Method for exception handling.
     *
     * @param ex exception.
     * @return
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handle(EntityNotFoundException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(ex.getClass().getSimpleName(), ex.getMessage()));
    }

    /**
     * Method for exception handling.
     *
     * @param ex exception.
     * @return
     */
    @ExceptionHandler(IllegalCardTransactionOperation.class)
    public ResponseEntity<ErrorResponse> handle(IllegalCardTransactionOperation ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(ex.getClass().getSimpleName(), ex.getMessage()));
    }

    /**
     * Method for exception handling.
     *
     * @param ex exception.
     * @return
     */
    @ExceptionHandler(IllegalDeleteOperation.class)
    public ResponseEntity<ErrorResponse> handle(IllegalDeleteOperation ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(ex.getClass().getSimpleName(), ex.getMessage()));
    }

    /**
     * Method for exception handling.
     *
     * @param ex exception.
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handle(HttpRequestMethodNotSupportedException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(ex.getClass().getSimpleName(), ex.getMessage()));
    }

    /**
     * Method for exception handling.
     *
     * @param ex exception.
     * @return
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handle(AccessDeniedException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(ex.getClass().getSimpleName(), ex.getMessage()));
    }

    /**
     * Method for exception handling.
     *
     * @param ex exception.
     * @return
     */
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponse> handle(UsernameNotFoundException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(ex.getClass().getSimpleName(), ex.getMessage()));
    }

    /**
     * Method for exception handling.
     *
     * @param ex exception.
     * @return
     */
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

    /**
     * Method for exception handling.
     *
     * @param ex exception.
     * @return
     */
    @ExceptionHandler(javax.persistence.EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handle(javax.persistence.EntityNotFoundException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(ex.getClass().getSimpleName(), ex.getMessage()));
    }

    /**
     * Method for exception handling.
     *
     * @param ex exception.
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGloballyInternalServerError(Exception ex) {
        if (ex instanceof NullPointerException) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }


}
