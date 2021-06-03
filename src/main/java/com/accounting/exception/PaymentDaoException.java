package com.accounting.exception;

public class PaymentDaoException extends RuntimeException {
    public PaymentDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
