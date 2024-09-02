package com.flag3.tradingappbackend.db.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.flag3.tradingappbackend.db.entity.ItemEntity;
import com.flag3.tradingappbackend.db.enums.ItemCategoryEnum;
import com.flag3.tradingappbackend.db.enums.ItemStatusEnum;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record ItemDto(
        UUID id,

        String name,

        double price,

        String description,

        @JsonProperty("media_urls")
        List<String> mediaUrls,

        ItemStatusEnum status,

        @JsonProperty("created_at")
        LocalDateTime createdAt,

        String address,

        ItemCategoryEnum category,

        UserDto owner
) {

    public ItemDto(ItemEntity itemEntity) {
        this(
                itemEntity.getId(),
                itemEntity.getName(),
                itemEntity.getPrice(),
                itemEntity.getDescription(),
                itemEntity.getMediaUrls(),
                itemEntity.getStatus(),
                itemEntity.getCreatedAt(),
                itemEntity.getAddress(),
                itemEntity.getCategory(),
                new UserDto(itemEntity.getUser())
        );
    }

}
