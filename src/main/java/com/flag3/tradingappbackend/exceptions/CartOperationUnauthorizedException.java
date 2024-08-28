package com.flag3.tradingappbackend.exceptions;

public class CartOperationUnauthorizedException extends RuntimeException{
    public CartOperationUnauthorizedException(String msg) {
        super(msg);
    }
}
