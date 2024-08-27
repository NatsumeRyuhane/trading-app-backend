package com.flag3.tradingappbackend.exceptions;

// This exception is thrown when a transaction item is invalid, for example,
// the item is taken down or already sold

public class TransactionItemInvalidException extends RuntimeException {

    // Default constructor
    public TransactionItemInvalidException() {
        super("Transaction item is invalid.");
    }

    // Constructor with custom message
    public TransactionItemInvalidException(String message) {
        super(message);
    }

    // Constructor with custom message and cause
    public TransactionItemInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    // Constructor with cause
    public TransactionItemInvalidException(Throwable cause) {
        super(cause);
    }
}