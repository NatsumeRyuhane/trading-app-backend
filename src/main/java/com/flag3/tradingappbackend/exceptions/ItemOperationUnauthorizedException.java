package com.flag3.tradingappbackend.exceptions;

public class ItemOperationUnauthorizedException extends RuntimeException{
    public ItemOperationUnauthorizedException(String msg) {
        super(msg);
    }
}
