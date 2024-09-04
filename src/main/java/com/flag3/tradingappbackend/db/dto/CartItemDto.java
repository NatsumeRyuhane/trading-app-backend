package com.flag3.tradingappbackend.db.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.flag3.tradingappbackend.db.entity.CartItemEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public record CartItemDto(
        UUID id,

        @JsonProperty("user_id")
        UUID userId,

        ItemDto item,

        @JsonProperty("created_at")
        LocalDateTime createdAt
) {

    public CartItemDto(CartItemEntity cartItemEntity) {
        this(
                cartItemEntity.getId(),
                cartItemEntity.getUserId(),
                new ItemDto(cartItemEntity.getItem()),
                cartItemEntity.getCreatedAt()
        );
    }

}
