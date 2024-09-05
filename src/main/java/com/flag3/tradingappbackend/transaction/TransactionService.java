package com.flag3.tradingappbackend.transaction;

import com.flag3.tradingappbackend.db.ItemRepository;
import com.flag3.tradingappbackend.db.TransactionRepository;
import com.flag3.tradingappbackend.db.UserRepository;
import com.flag3.tradingappbackend.db.dto.TransactionDto;
import com.flag3.tradingappbackend.db.entity.ItemEntity;
import com.flag3.tradingappbackend.db.entity.TransactionEntity;
import com.flag3.tradingappbackend.db.enums.ItemStatusEnum;
import com.flag3.tradingappbackend.db.enums.TransactionStatusEnum;
import com.flag3.tradingappbackend.exceptions.AssetDoesNotExistException;
import com.flag3.tradingappbackend.exceptions.TransactionItemInvalidException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    public void createTransaction(UUID buyerId, UUID sellerId, UUID itemId) {
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
                TransactionStatusEnum.IN_PROGRESS  // Initial status
        );

        // Update the item status to ONGOING_TRADE so no more transactions can be created with the item
        itemRepository.updateStatus(itemId, ItemStatusEnum.ONGOING_TRADE.name());

        // Save the transaction to the database
        transactionRepository.save(transactionEntity);
    }

    public TransactionDto getTransactionById(UUID id) {
        TransactionEntity transactionEntity = transactionRepository.findById(id)
                .orElseThrow(() -> new AssetDoesNotExistException("Transaction"));
        return new TransactionDto(transactionEntity);
    }


    public List<TransactionDto> getTransactionsBySellerId(UUID sellerId) {
        return transactionRepository.findAllBySellerId(sellerId)
                .stream()
                .map(TransactionDto::new)
                .toList();
    }

    public List<TransactionDto> getTransactionsByBuyerId(UUID buyerId) {
        return transactionRepository.findAllByBuyerId(buyerId)
                .stream()
                .map(TransactionDto::new)
                .toList();
    }

    public List<TransactionDto> getTransactionsByItemId(UUID itemId) {
        return transactionRepository.findAllByItemId(itemId)
                .stream()
                .map(TransactionDto::new)
                .toList();
    }

    public List<TransactionDto> getTransactionsByStatus(TransactionStatusEnum statusEnum) {
        return transactionRepository.findAllByStatus(statusEnum)
                .stream()
                .map(TransactionDto::new)
                .toList();
    }

    public void updateStatus(UUID id, TransactionStatusEnum status) {
        transactionRepository.updateStatus(id, status.name());
    }

    public void updateShipped(UUID id) {
        transactionRepository.updateShippedAt(id, LocalDateTime.now());
        transactionRepository.updateStatus(id, TransactionStatusEnum.SHIPPED.name());
    }

    public void updateDelivered(UUID id) {
        transactionRepository.updateDeliveredAt(id, LocalDateTime.now());
        transactionRepository.updateStatus(id, TransactionStatusEnum.DELIVERED.name());
    }

    public void updateConfirmed(UUID id) {
        transactionRepository.updateConfirmedAt(id, LocalDateTime.now());
        transactionRepository.updateStatus(id, TransactionStatusEnum.CONFIRMED.name());

        // Update the status of the associated item to SOLD
        TransactionEntity transaction = transactionRepository.getReferenceById(id);
        ItemEntity item = itemRepository.getReferenceById(transaction.getItemId());
        itemRepository.updateStatus(item.getId(), ItemStatusEnum.SOLD.name());
    }

    public void updateCanceled(UUID id) {
        transactionRepository.updateCanceledAt(id, LocalDateTime.now());
        transactionRepository.updateStatus(id, TransactionStatusEnum.CANCELED.name());

        // Set the status of the item back to AVAILABLE after cancellation
        TransactionEntity transaction = transactionRepository.getReferenceById(id);
        itemRepository.updateStatus(transaction.getItemId(), ItemStatusEnum.AVAILABLE.name());
    }

    public void updateBuyerToSellerRating(UUID id, Double rating) {
        transactionRepository.updateBuyerToSellerRating(id, rating);
    }

}