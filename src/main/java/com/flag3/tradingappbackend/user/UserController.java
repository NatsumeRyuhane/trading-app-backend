package com.flag3.tradingappbackend.user;

import com.flag3.tradingappbackend.db.dto.TransactionDto;
import com.flag3.tradingappbackend.db.entity.TransactionEntity;
import com.flag3.tradingappbackend.transaction.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class UserController {
    private final TransactionService transactionService;
    private final UserService userService;

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest body) {
        userService.register(body.username(), body.password(), body.firstName(), body.lastName(), body.address());
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest body) {
        String token = userService.login(body.username(), body.password());
        return new LoginResponse(token);
    }

    @PostMapping("/get/users/rating/{id}")
    public RatingResponse getRating(@PathVariable UUID id) {
        List<TransactionDto> transactionList = transactionService.getTransactionsByBuyerId(id);
        double total = 0.0;
        int size = 0;
        for (TransactionDto i : transactionList) {
            if (i.buyerToSellerRating() != null) {
                total += i.buyerToSellerRating();
                size++;
            }
        }
        return new RatingResponse(total, size);
    }
}
