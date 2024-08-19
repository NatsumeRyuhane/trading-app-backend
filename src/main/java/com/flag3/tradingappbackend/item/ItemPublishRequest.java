package com.flag3.tradingappbackend.item;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.flag3.tradingappbackend.db.enums.ItemStatusEnum;

public record ItemPublishRequest(
        String name,
        String description,
        double price,
        ItemStatusEnum status,
        @JsonProperty("media_urls")
        String mediaUrls,
        String address
) {
}
