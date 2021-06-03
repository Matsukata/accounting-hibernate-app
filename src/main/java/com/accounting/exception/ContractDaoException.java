package com.accounting.exception;

public class ContractDaoException extends RuntimeException{
    public ContractDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
