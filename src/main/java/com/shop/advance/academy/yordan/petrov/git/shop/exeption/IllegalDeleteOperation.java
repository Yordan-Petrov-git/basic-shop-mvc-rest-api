package com.shop.advance.academy.yordan.petrov.git.shop.exeption;

/**
 * Class handling the exception on delete operation.
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
public class IllegalDeleteOperation extends RuntimeException {

    /**
     *
     */
    public IllegalDeleteOperation() {
    }

    /**
     * @param message
     */
    public IllegalDeleteOperation(String message) {
        super(message);
    }

    /**
     * @param message
     * @param cause
     */
    public IllegalDeleteOperation(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param cause
     */
    public IllegalDeleteOperation(Throwable cause) {
        super(cause);
    }
}
