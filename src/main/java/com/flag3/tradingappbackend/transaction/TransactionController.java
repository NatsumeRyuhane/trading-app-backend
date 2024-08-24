package com.flag3.tradingappbackend.transaction;

import com.flag3.tradingappbackend.db.entity.TransactionEntity;
import com.flag3.tradingappbackend.db.entity.UserEntity;
import com.flag3.tradingappbackend.db.enums.TransactionStatusEnum;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/transactions")
@AllArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionEntity> addTransaction(
            @AuthenticationPrincipal UserEntity buyer,  // This is because a transaction is always initiated by a buyer that is logged in
            @RequestParam UUID sellerId,
            @RequestParam UUID itemId
    ) {
        TransactionEntity createdTransaction = transactionService.createTransaction(buyer.getId(), sellerId, itemId);
        return ResponseEntity.ok(createdTransaction);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionEntity> getTransactionById(@PathVariable UUID id) {
        Optional<TransactionEntity> transaction = transactionService.getTransactionById(id);
        return transaction.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // I think the purposes of these two APIs are to display all the transactions of the current logged-in user either as
    // buyer or seller, so I used the id of the current user instead of having to pass the id in as a path variable
    @GetMapping("/seller")
    public List<TransactionEntity> getTransactionsAsSeller(@AuthenticationPrincipal UserEntity user) {
        return transactionService.getTransactionsBySellerId(user.getId());
    }

    @GetMapping("/buyer")
    public List<TransactionEntity> getTransactionsAsBuyer(@AuthenticationPrincipal UserEntity user) {
        return transactionService.getTransactionsByBuyerId(user.getId());
    }

    @GetMapping("/item/{itemId}")
    public List<TransactionEntity> getTransactionsByItemId(@PathVariable UUID itemId) {
        return transactionService.getTransactionsByItemId(itemId);
    }

    @GetMapping("/status/{status}")
    public List<TransactionEntity> getTransactionsByStatus(@PathVariable TransactionStatusEnum status) {
        return transactionService.getTransactionsByStatus(status);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateStatus(@PathVariable UUID id, @RequestParam TransactionStatusEnum status) {
        transactionService.updateStatus(id, status);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/shipped")
    public ResponseEntity<Void> updateShippedAt(@PathVariable UUID id, @RequestParam LocalDateTime timeValue) {
        transactionService.updateShippedAt(id, timeValue, TransactionStatusEnum.SHIPPED);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/delivered")
    public ResponseEntity<Void> updateDeliveredAt(@PathVariable UUID id, @RequestParam LocalDateTime timeValue) {
        transactionService.updateDeliveredAt(id, timeValue, TransactionStatusEnum.DELIVERED);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/confirmed")
    public ResponseEntity<Void> updateConfirmedAt(@PathVariable UUID id, @RequestParam LocalDateTime timeValue) {
        transactionService.updateConfirmedAt(id, timeValue, TransactionStatusEnum.CONFIRMED);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/canceled")
    public ResponseEntity<Void> updateCanceledAt(@PathVariable UUID id, @RequestParam LocalDateTime timeValue) {
        transactionService.updateCanceledAt(id, timeValue, TransactionStatusEnum.CANCELED);
        return ResponseEntity.ok().build();
    }
}