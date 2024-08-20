package com.flag3.tradingappbackend.transaction;

import com.flag3.tradingappbackend.db.entity.TransactionEntity;
import com.flag3.tradingappbackend.db.enums.TranscationStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @PostMapping
    public ResponseEntity<TransactionEntity> addTransaction(
            @RequestParam UUID buyerId,
            @RequestParam UUID sellerId,
            @RequestParam UUID itemId,
            @RequestParam double amount) {
        TransactionEntity createdTransaction = transactionService.createTransaction(buyerId, sellerId, itemId, amount);
        return ResponseEntity.ok(createdTransaction);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionEntity> getTransactionById(@PathVariable UUID id) {
        Optional<TransactionEntity> transaction = transactionService.getTransactionById(id);
        return transaction.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping("/seller/{sellerId}")
    public List<TransactionEntity> getTransactionsBySellerId(@PathVariable UUID sellerId) {
        return transactionService.getTransactionsBySellerId(sellerId);
    }

    @GetMapping("/buyer/{buyerId}")
    public List<TransactionEntity> getTransactionsByBuyerId(@PathVariable UUID buyerId) {
        return transactionService.getTransactionsByBuyerId(buyerId);
    }

    @GetMapping("/item/{itemId}")
    public List<TransactionEntity> getTransactionsByItemId(@PathVariable UUID itemId) {
        return transactionService.getTransactionsByItemId(itemId);
    }

    @GetMapping("/status/{status}")
    public List<TransactionEntity> getTransactionsByStatus(@PathVariable TranscationStatusEnum status) {
        return transactionService.getTransactionsByStatus(status);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateStatus(@PathVariable UUID id, @RequestParam TranscationStatusEnum status) {
        transactionService.updateStatus(id, status);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/shipped")
    public ResponseEntity<Void> updateShippedAt(@PathVariable UUID id, @RequestParam LocalDateTime timeValue) {
        transactionService.updateShippedAt(id, timeValue, TranscationStatusEnum.SHIPPED);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/delivered")
    public ResponseEntity<Void> updateDeliveredAt(@PathVariable UUID id, @RequestParam LocalDateTime timeValue) {
        transactionService.updateDeliveredAt(id, timeValue, TranscationStatusEnum.DELIVERED);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/confirmed")
    public ResponseEntity<Void> updateConfirmedAt(@PathVariable UUID id, @RequestParam LocalDateTime timeValue) {
        transactionService.updateConfirmedAt(id, timeValue, TranscationStatusEnum.CONFIRMED);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/canceled")
    public ResponseEntity<Void> updateCanceledAt(@PathVariable UUID id, @RequestParam LocalDateTime timeValue) {
        transactionService.updateCanceledAt(id, timeValue, TranscationStatusEnum.CANCELED);
        return ResponseEntity.ok().build();
    }
}