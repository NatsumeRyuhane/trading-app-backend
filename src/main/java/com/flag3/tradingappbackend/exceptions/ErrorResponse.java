package com.flag3.tradingappbackend.exceptions;

public record ErrorResponse(
        String message,
        String error
) {
}
