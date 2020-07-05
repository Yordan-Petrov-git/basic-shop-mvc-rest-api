package com.shop.advance.academy.yordan.petrov.git.shop.exeption;

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
