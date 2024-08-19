package com.flag3.tradingappbackend.item;

import com.flag3.tradingappbackend.db.enums.ItemStatusEnum;

public record ItemPublishRequest(
        String name,
        String description,
        double price,
        ItemStatusEnum status,
        String mediaUrls,
        String address
) {
}
