package com.flag3.tradingappbackend.cart;

import com.flag3.tradingappbackend.db.entity.CartEntity;
import com.flag3.tradingappbackend.db.entity.ItemEntity;
import com.flag3.tradingappbackend.db.entity.UserEntity;
import com.flag3.tradingappbackend.db.enums.ItemStatusEnum;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/carts")
@AllArgsConstructor
public class CartController {
    private final CartService cartService;

    private final UserEntity user = new UserEntity(UUID.fromString("e18452c5-c2d0-492f-b53f-a62121adc466"), "stevenysy", "secret", "steven", "yi", "123 Main Street", true, LocalDateTime.now());

    @GetMapping
    public List<CartEntity> getAllCarts() {
        return cartService.getAllCarts();
    }

    @GetMapping("/mine")
    public List<CartEntity> getCartListing() {
        return cartService.getAllCarts()
                .stream()
                .filter(cart -> cart.getUserId().equals(user.getId()))
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postCart(@RequestBody CartPublishRequest body) {
        cartService.createCart(
                user.getId(),
                body.cartId(),
                body.items()
        );
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable String id) {
        cartService.deleteCart(user.getId(), UUID.fromString(id));
    }
}
