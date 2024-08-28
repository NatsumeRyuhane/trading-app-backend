package com.flag3.tradingappbackend.item;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.flag3.tradingappbackend.db.enums.ItemStatusEnum;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record ItemPublishRequest(
        String name,
        String description,
        double price,
        ItemStatusEnum status,
        List<MultipartFile> media,
        String address
) {
}
