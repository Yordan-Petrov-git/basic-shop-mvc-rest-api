package com.shop.advance.academy.yordan.petrov.git.shop.exeption;
/**
 * Class handling the exception on delete operation.
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
public class IllegalDeleteOperation extends RuntimeException {

    public IllegalDeleteOperation() {
    }

    public IllegalDeleteOperation(String message) {
        super(message);
    }

    public IllegalDeleteOperation(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalDeleteOperation(Throwable cause) {
        super(cause);
    }
}
