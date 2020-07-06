package com.shop.advance.academy.yordan.petrov.git.shop.exeption;

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
