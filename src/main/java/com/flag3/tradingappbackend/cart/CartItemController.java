package com.flag3.tradingappbackend.cart;

import com.flag3.tradingappbackend.db.entity.CartItemEntity;
import com.flag3.tradingappbackend.db.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cart_items")
@AllArgsConstructor
public class CartItemController {
    private final CartItemService cartItemService;

    private final UserEntity user = new UserEntity(UUID.fromString("e18452c5-c2d0-492f-b53f-a62121adc466"), "stevenysy", "secret", "steven", "yi", "123 Main Street", true, LocalDateTime.now());

    @GetMapping
    public List<CartItemEntity> getAllCartItems() {
        return cartItemService.getAllCartItems();
    }

    @GetMapping("/mine")
    public List<CartItemEntity> getCartListing() {
        return cartItemService.getAllCartItems()
                .stream()
                .filter(cart -> cart.getUserId().equals(user.getId()))
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postCart(@RequestBody CartPublishRequest body) {
        cartItemService.createCart(
                user.getId(),
                body.itemId(),
                body.createdAt()
        );
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable String id) {
        cartItemService.deleteCartItem(user.getId(), UUID.fromString(id));
    }
}
