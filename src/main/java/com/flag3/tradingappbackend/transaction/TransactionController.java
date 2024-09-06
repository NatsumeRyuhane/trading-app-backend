package com.flag3.tradingappbackend.transaction;

import com.flag3.tradingappbackend.db.dto.TransactionDto;
import com.flag3.tradingappbackend.db.entity.TransactionEntity;
import com.flag3.tradingappbackend.db.entity.UserEntity;
import com.flag3.tradingappbackend.db.enums.TransactionStatusEnum;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Void> addTransaction(
            @AuthenticationPrincipal UserEntity buyer,  // This is because a transaction is always initiated by a buyer that is logged in
            @RequestParam UUID sellerId,
            @RequestParam UUID itemId
    ) {
        transactionService.createTransaction(buyer.getId(), sellerId, itemId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDto> getTransactionById(@PathVariable UUID id) {
        return ResponseEntity.ok(transactionService.getTransactionById(id));
    }

    // I think the purposes of these two APIs are to display all the transactions of the current logged-in owner either as
    // buyer or seller, so I used the id of the current owner instead of having to pass the id in as a path variable
    @GetMapping("/seller")
    public List<TransactionDto> getTransactionsAsSeller(@AuthenticationPrincipal UserEntity user) {
        return transactionService.getTransactionsBySellerId(user.getId());
    }

    @GetMapping("/buyer")
    public List<TransactionDto> getTransactionsAsBuyer(@AuthenticationPrincipal UserEntity user) {
        return transactionService.getTransactionsByBuyerId(user.getId());
    }

    @GetMapping("/item/{itemId}")
    public List<TransactionDto> getTransactionsByItemId(@PathVariable UUID itemId) {
        return transactionService.getTransactionsByItemId(itemId);
    }

    @GetMapping
    public TransactionDto getActiveTransactionByItemId(@RequestParam UUID itemId) {
        return transactionService.getActiveTransactionByItemId(itemId);
    }

    @GetMapping("/status/{status}")
    public List<TransactionDto> getTransactionsByStatus(@PathVariable TransactionStatusEnum status) {
        return transactionService.getTransactionsByStatus(status);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateStatus(@PathVariable UUID id, @RequestParam TransactionStatusEnum status) {
        transactionService.updateStatus(id, status);
        return ResponseEntity.ok().build();
    }

    // I moved the timestamp and the status into the service
    @PatchMapping("/{id}/shipped")
    public ResponseEntity<Void> updateShipped(@PathVariable UUID id) {
        transactionService.updateShipped(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/delivered")
    public ResponseEntity<Void> updateDelivered(@PathVariable UUID id) {
        transactionService.updateDelivered(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/confirmed")
    public ResponseEntity<Void> updateConfirmedAt(@PathVariable UUID id) {
        transactionService.updateConfirmed(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/canceled")
    public ResponseEntity<Void> updateCanceledAt(@PathVariable UUID id) {
        transactionService.updateCanceled(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/rating")
    public ResponseEntity<Void> updateBuyerToSellerRating(@PathVariable UUID id, @RequestParam Double rating) {
        transactionService.updateBuyerToSellerRating(id, rating);
        return ResponseEntity.ok().build();
    }

}