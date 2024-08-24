package com.flag3.tradingappbackend.db.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.flag3.tradingappbackend.db.entity.TransactionEntity;
import com.flag3.tradingappbackend.db.enums.TransactionStatusEnum;
import jakarta.persistence.Column;

import java.time.LocalDateTime;
import java.util.UUID;

public record TransactionDto(
        UUID id,

        TransactionStatusEnum status,

        @JsonProperty("buyer_to_seller_rating")
        Integer buyerToSellerRating,

        @JsonProperty("seller_to_buyer_rating")
        Integer sellerToBuyerRating,

        @JsonProperty("created_at")
        LocalDateTime createdAt,

        @JsonProperty("shipped_at")
        LocalDateTime shippedAt,

        @JsonProperty("delivered_at")
        LocalDateTime deliveredAt,

        @JsonProperty("confirmed_at")
        LocalDateTime confirmedAt,

        @JsonProperty("canceled_at")
        LocalDateTime canceledAt,

        ItemDto item,

        UserDto buyer,

        UserDto seller
) {

    public TransactionDto(TransactionEntity transactionEntity) {
        this(
                transactionEntity.getId(),
                transactionEntity.getStatus(),
                transactionEntity.getBuyerToSellerRating(),
                transactionEntity.getSellerToBuyerRating(),
                transactionEntity.getCreatedAt(),
                transactionEntity.getShippedAt(),
                transactionEntity.getDeliveredAt(),
                transactionEntity.getConfirmedAt(),
                transactionEntity.getCanceledAt(),
                new ItemDto(transactionEntity.getItem()),
                new UserDto(transactionEntity.getBuyer()),
                new UserDto(transactionEntity.getSeller())
        );
    }

}
