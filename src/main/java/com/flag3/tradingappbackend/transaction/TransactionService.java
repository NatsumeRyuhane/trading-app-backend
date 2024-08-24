package com.flag3.tradingappbackend.transaction;

import com.flag3.tradingappbackend.db.ItemRepository;
import com.flag3.tradingappbackend.db.TransactionRepository;
import com.flag3.tradingappbackend.db.entity.ItemEntity;
import com.flag3.tradingappbackend.db.entity.TransactionEntity;
import com.flag3.tradingappbackend.db.enums.ItemStatusEnum;
import com.flag3.tradingappbackend.db.enums.TransactionStatusEnum;
import com.flag3.tradingappbackend.exceptions.TransactionItemInvalidException;
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

    public TransactionEntity createTransaction(UUID buyerId, UUID sellerId, UUID itemId) {
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
                TransactionStatusEnum.PENDING  // Initial status
        );

        // Update the item status to SOLD so no more transactions can be created with the item
        itemRepository.updateStatus(itemId, ItemStatusEnum.SOLD);

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

    public List<TransactionEntity> getTransactionsByStatus(TransactionStatusEnum statusEnum) {
        return transactionRepository.findAllByStatus(statusEnum);
    }
    public void updateStatus(UUID id, TransactionStatusEnum status) {
        transactionRepository.updateStatus(id, status);
    }

    public void updateShipped(UUID id) {
        transactionRepository.updateShippedAt(id, LocalDateTime.now());
        transactionRepository.updateStatus(id, TransactionStatusEnum.SHIPPED);
    }

    public void updateDelivered(UUID id) {
        transactionRepository.updateDeliveredAt(id, LocalDateTime.now());
        transactionRepository.updateStatus(id, TransactionStatusEnum.DELIVERED);
    }

    public void updateConfirmed(UUID id) {
        transactionRepository.updateConfirmedAt(id, LocalDateTime.now());
        transactionRepository.updateStatus(id, TransactionStatusEnum.CONFIRMED);
    }

    public void updateCanceled(UUID id) {
        transactionRepository.updateCanceledAt(id, LocalDateTime.now());
        transactionRepository.updateStatus(id, TransactionStatusEnum.CANCELED);

        // Set the status of the item back to AVAILABLE after cancellation
        TransactionEntity transaction = transactionRepository.getReferenceById(id);
        itemRepository.updateStatus(transaction.getItemId(), ItemStatusEnum.AVAILABLE);
    }
}