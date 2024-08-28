package com.flag3.tradingappbackend.cart;

import com.flag3.tradingappbackend.db.entity.ItemEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record CartPublishRequest(
        UUID itemId,
        LocalDateTime createdAt
) {
}
