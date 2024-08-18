package com.flag3.tradingappbackend.user;

public record RegisterRequest(
        String username,
        String password,
        String firstName,
        String lastName,
        String address
) {
}
