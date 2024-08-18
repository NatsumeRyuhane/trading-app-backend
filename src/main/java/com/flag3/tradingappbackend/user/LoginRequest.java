package com.flag3.tradingappbackend.user;

public record LoginRequest(
        String username,
        String password
) {
}
