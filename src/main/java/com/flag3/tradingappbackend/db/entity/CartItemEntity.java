package com.flag3.tradingappbackend.db.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "cart_items")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CartItemEntity {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "item_id", nullable = false)
    private UUID itemId;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "item_id", foreignKey = @ForeignKey(name = "fk_item_cart"), insertable = false, updatable = false)
    private ItemEntity item;

    public CartItemEntity(UUID uuid, UUID userId, UUID itemId, LocalDateTime createdAt) {
        this.id = uuid;
        this.userId = userId;
        this.itemId = itemId;
        this.createdAt = createdAt;
    }

}
