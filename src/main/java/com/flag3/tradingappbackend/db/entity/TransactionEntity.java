package com.flag3.tradingappbackend.db.entity;



import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import com.flag3.tradingappbackend.db.enums.TransactionStatusEnum;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transactions")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class TransactionEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "item_id", nullable = false)
    private UUID itemId;

    @Column(name = "buyer_id", nullable = false)
    private UUID buyerId;

    @Column(name = "seller_id", nullable = false)
    private UUID sellerId;

    @Column(name = "status", nullable = false)
    private TransactionStatusEnum status;

    @Column(name = "buyer_to_seller_rating")
    private Integer buyerToSellerRating;

    @Column(name = "seller_to_buyer_rating")
    private Integer sellerToBuyerRating;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "shipped_at")
    private LocalDateTime shippedAt;

    @Column(name = "delivered_at")
    private LocalDateTime deliveredAt;

    @Column(name = "confirmed_at")
    private LocalDateTime confirmedAt;

    @Column(name = "canceled_at")
    private LocalDateTime canceledAt;

    @ManyToOne
    @JoinColumn(name = "buyer_id", foreignKey = @ForeignKey(name = "fk_transaction_buyer"), insertable = false, updatable = false)
    private UserEntity buyer;

    @ManyToOne
    @JoinColumn(name = "seller_id", foreignKey = @ForeignKey(name = "fk_transaction_seller"), insertable = false, updatable = false)
    private UserEntity seller;

    @ManyToOne
    @JoinColumn(name = "item_id", foreignKey = @ForeignKey(name = "fk_transaction_item"), insertable = false, updatable = false)
    private ItemEntity item;

    public TransactionEntity(
            UUID id,
            UUID itemId,
            UUID buyerId,
            UUID sellerId,
            TransactionStatusEnum status
    ) {
        this.id = id;
        this.itemId = itemId;
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.status = status;
    }

    // No need to manually write constructors, getters, equals, hashCode, or toString methods
    // Lombok will generate them automatically based on the annotations used.
}
