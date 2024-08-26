package com.flag3.tradingappbackend.db.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "carts")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CartEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "cart_id", nullable = false)
    private UUID cartId;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemEntity> items;

    public CartEntity(UUID uuid, UUID userId, UUID cartId, List<ItemEntity> items) {
        this.id = uuid;
        this.userId = userId;
        this.cartId = cartId;
        this.items = items;
    }
}
