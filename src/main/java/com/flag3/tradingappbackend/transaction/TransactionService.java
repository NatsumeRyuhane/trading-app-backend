package com.flag3.tradingappbackend.transaction;

import com.flag3.tradingappbackend.db.ItemRepository;
import com.flag3.tradingappbackend.db.TransactionRepository;
import com.flag3.tradingappbackend.db.entity.ItemEntity;
import com.flag3.tradingappbackend.db.entity.TransactionEntity;
import com.flag3.tradingappbackend.db.enums.ItemStatusEnum;
import com.flag3.tradingappbackend.db.enums.TranscationStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, ItemRepository itemRepository) {
        this.transactionRepository = transactionRepository;
        this.itemRepository = itemRepository;
    }

    public TransactionEntity createTransaction(UUID buyerId, UUID sellerId, UUID itemId, double amount) {
        // Fetch the item to ensure it exists and is valid for a transaction
        ItemEntity item = itemRepository.findById(itemId)
                .orElseThrow(() -> new TransactionItemInvalidException("Item not found or is no longer available"));

        // Additional validation: Check if the item is already sold or invalid
        if (item.getStatus() != ItemStatusEnum.AVAILABLE) {
            throw new TransactionItemInvalidException("Item is not available for purchase");
        }

        // Create a new transaction entity
        TransactionEntity transactionEntity = new TransactionEntity(
                UUID.randomUUID(),             // Transaction ID
                itemId,                        // Associated item ID
                buyerId,                       // Buyer ID
                sellerId,                      // Seller ID
                TranscationStatusEnum.PENDING, // Initial status
                -1,                             // Initial buyer to seller rating
                -1,                             // Initial seller to buyer rating
                LocalDateTime.now(),           // Created at timestamp
                null,                          // Shipped at (not shipped yet)
                null,                          // Delivered at (not delivered yet)
                null,                          // Confirmed at (not confirmed yet)
                null                           // Canceled at (not canceled yet)
        );

        // Save the transaction to the database
        return transactionRepository.save(transactionEntity);
    }

    public Optional<TransactionEntity> getTransactionById(UUID id) {
        return transactionRepository.findById(id);
    }


    public List<TransactionEntity> getTransactionsBySellerId(UUID sellerId) {
        return transactionRepository.findAllBySellerId(sellerId);
    }

    public List<TransactionEntity> getTransactionsByBuyerId(UUID buyerId) {
        return transactionRepository.findAllByBuyerId(buyerId);
    }

    public List<TransactionEntity> getTransactionsByItemId(UUID itemId) {
        return transactionRepository.findAllByItemId(itemId);
    }

    public List<TransactionEntity> getTransactionsByStatus(TranscationStatusEnum statusEnum) {
        return transactionRepository.findAllByStatus(statusEnum);
    }
    public void updateStatus(UUID id, TranscationStatusEnum status) {
        transactionRepository.updateStatus(id, status);
    }

    public void updateShippedAt(UUID id, LocalDateTime timeValue, TranscationStatusEnum status) {
        transactionRepository.updateShippedAt(id, timeValue);
        transactionRepository.updateStatus(id, status);
    }

    public void updateDeliveredAt(UUID id, LocalDateTime timeValue, TranscationStatusEnum status) {
        transactionRepository.updateDeliveredAt(id, timeValue);
        transactionRepository.updateStatus(id, status);
    }

    public void updateConfirmedAt(UUID id, LocalDateTime timeValue, TranscationStatusEnum status) {
        transactionRepository.updateConfirmedAt(id, timeValue);
        transactionRepository.updateStatus(id, status);
    }

    public void updateCanceledAt(UUID id, LocalDateTime timeValue, TranscationStatusEnum status) {
        transactionRepository.updateCanceledAt(id, timeValue);
        transactionRepository.updateStatus(id, status);
    }
}