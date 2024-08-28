package com.flag3.tradingappbackend.exceptions;

public class AssetDoesNotExistException extends RuntimeException {
    public AssetDoesNotExistException(String asset) {
        super(asset + " does not exist or is no longer active");
    }
}
