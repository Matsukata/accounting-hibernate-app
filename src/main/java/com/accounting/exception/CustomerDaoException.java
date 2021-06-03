package com.accounting.exception;

public class CustomerDaoException extends RuntimeException{
    public CustomerDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
