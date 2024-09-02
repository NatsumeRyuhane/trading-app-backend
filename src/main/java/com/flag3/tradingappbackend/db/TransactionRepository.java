package com.flag3.tradingappbackend.db;


import com.flag3.tradingappbackend.db.entity.TransactionEntity;
import com.flag3.tradingappbackend.db.enums.TransactionStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, UUID> {

    Optional<TransactionEntity> findById(UUID id);

    List<TransactionEntity> findAllBySellerId(UUID sellerId);
    List<TransactionEntity> findAllByBuyerId(UUID buyerId);
    List<TransactionEntity> findAllByItemId(UUID itemId);

    List<TransactionEntity> findAllByStatus(TransactionStatusEnum statusEnum);

    @Modifying
    @Transactional
    @Query("UPDATE TransactionEntity t SET t.status = :status WHERE t.id = :id")
    int updateStatus(UUID id, String status);

    @Modifying
    @Transactional
    @Query("UPDATE TransactionEntity t SET t.shippedAt = :timeValue WHERE t.id = :id")
    int updateShippedAt(UUID id, LocalDateTime timeValue);

    @Modifying
    @Transactional
    @Query("UPDATE TransactionEntity t SET t.deliveredAt = :timeValue WHERE t.id = :id")
    int updateDeliveredAt(UUID id, LocalDateTime timeValue);

    @Modifying
    @Transactional
    @Query("UPDATE TransactionEntity t SET t.confirmedAt = :timeValue WHERE t.id = :id")
    int updateConfirmedAt(UUID id, LocalDateTime timeValue);

    @Modifying
    @Transactional
    @Query("UPDATE TransactionEntity t SET t.canceledAt = :timeValue WHERE t.id = :id")
    int updateCanceledAt(UUID id, LocalDateTime timeValue);

    @Modifying
    @Transactional
    @Query("UPDATE TransactionEntity t SET t.buyerToSellerRating = :rating WHERE t.id = :id")
    int updateBuyerToSellerRating(UUID id, Double rating);

}
