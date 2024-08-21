package com.flag3.tradingappbackend.db.entity;

import com.flag3.tradingappbackend.db.enums.ItemStatusEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "items")
@NoArgsConstructor
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

    @Column(name = "media_urls")
    private List<String> mediaUrls;

    @Column(nullable = false)
    private ItemStatusEnum status = ItemStatusEnum.AVAILABLE;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column
    private String address;

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_item_user"), insertable = false, updatable = false)
    private UserEntity user;

    public ItemEntity(
            UUID id,
            UUID userId,
            String name,
            double price,
            String description,
            List<String> mediaUrls,
            ItemStatusEnum status,
            String address
    ) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.mediaUrls = mediaUrls;
        this.status = status;
        this.address = address;
    }
}
