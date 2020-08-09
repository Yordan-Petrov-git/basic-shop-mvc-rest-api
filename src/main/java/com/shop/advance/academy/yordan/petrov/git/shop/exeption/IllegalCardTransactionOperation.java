package com.shop.advance.academy.yordan.petrov.git.shop.exeption;
/**
 * Class handling the exceptions on invalid card transaction.
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
public class IllegalCardTransactionOperation extends RuntimeException {

    public IllegalCardTransactionOperation() {
    }

    public IllegalCardTransactionOperation(String message) {
        super(message);
    }

    public IllegalCardTransactionOperation(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalCardTransactionOperation(Throwable cause) {
        super(cause);
    }
}
