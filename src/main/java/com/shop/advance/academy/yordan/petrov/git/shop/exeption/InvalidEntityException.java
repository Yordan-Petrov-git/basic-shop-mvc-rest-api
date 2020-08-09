package com.shop.advance.academy.yordan.petrov.git.shop.exeption;

/**
 * Class handling the exceptions for invalid entity.
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
public class InvalidEntityException extends RuntimeException {

    /**
     *
     */
    public InvalidEntityException() {
    }

    /**
     * @param message
     */
    public InvalidEntityException(String message) {
        super(message);
    }

    /**
     * @param message
     * @param cause
     */
    public InvalidEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param cause
     */
    public InvalidEntityException(Throwable cause) {
        super(cause);
    }
}

