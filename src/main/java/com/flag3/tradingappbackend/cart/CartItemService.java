package com.flag3.tradingappbackend.cart;

import com.flag3.tradingappbackend.db.CartItemRepository;
import com.flag3.tradingappbackend.db.entity.CartItemEntity;
import com.flag3.tradingappbackend.db.entity.ItemEntity;
import com.flag3.tradingappbackend.exceptions.CartOperationUnauthorizedException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CartItemService {
    private final CartItemRepository cartItemRepository;

    public List<CartItemEntity> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    public Optional<CartItemEntity> getCartById(UUID id) {
        return cartItemRepository.findById(id);
    }

    public List<CartItemEntity> getCartByUserId(UUID userId) {
        return cartItemRepository.findByUserId(userId);
    }

    public boolean cartItemExists(UUID id) {
        return cartItemRepository.existsById(id);
    }

    public void createCart(
            UUID userId,
            UUID cartId,
            LocalDateTime createdAt
    ) {
        CartItemEntity cartItemEntity = new CartItemEntity(
                UUID.randomUUID(),
                userId,
                cartId,
                createdAt
        );
        cartItemRepository.save(cartItemEntity);
    }


    public void deleteCartItem(UUID userId, UUID itemId) {
        CartItemEntity cartItemEntity = cartItemRepository.findById(itemId).get();
        if (cartItemEntity.getUserId().equals(userId)) {
            cartItemRepository.deleteById(userId);
        } else {
            throw new CartOperationUnauthorizedException("Unable to delete cart item: User does not own cart item");
        }
    }
}
