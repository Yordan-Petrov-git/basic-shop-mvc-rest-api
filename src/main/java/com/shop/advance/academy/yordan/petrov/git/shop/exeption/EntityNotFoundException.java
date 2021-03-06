package com.shop.advance.academy.yordan.petrov.git.shop.exeption;

/**
 * Class handling the exceptions when entity is not found.
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
public class EntityNotFoundException extends RuntimeException {

    /**
     *
     */
    public EntityNotFoundException() {
    }

    /**
     * @param message
     */
    public EntityNotFoundException(String message) {
        super(message);
    }

    /**
     * @param message
     * @param cause
     */
    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param cause
     */
    public EntityNotFoundException(Throwable cause) {
        super(cause);
    }
}
