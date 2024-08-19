package com.flag3.tradingappbackend.db.entity;

import com.flag3.tradingappbackend.db.enums.ItemStatusEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "items")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class ItemEntity {
    @Id
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double price;

    @Column
    private String description;

    // TODO: Add support for multi-media files
    @Column(name = "media_urls")
    private String mediaUrls;


    @Column(nullable = false)
    private ItemStatusEnum status = ItemStatusEnum.AVAILABLE;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column
    private String address;
}
