package com.shop.advance.academy.yordan.petrov.git.shop.exeption;


public class IllegalBankOperationException extends RuntimeException {

    public IllegalBankOperationException() {
    }

    public IllegalBankOperationException(String message) {
        super(message);
    }

    public IllegalBankOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalBankOperationException(Throwable cause) {
        super(cause);
    }


}
