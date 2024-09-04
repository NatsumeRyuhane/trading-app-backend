package com.flag3.tradingappbackend.cart;

import com.flag3.tradingappbackend.db.CartItemRepository;
import com.flag3.tradingappbackend.db.dto.CartItemDto;
import com.flag3.tradingappbackend.db.entity.CartItemEntity;
import com.flag3.tradingappbackend.db.entity.ItemEntity;
import com.flag3.tradingappbackend.exceptions.AssetDoesNotExistException;
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

    public Optional<CartItemEntity> getCartItemsById(UUID id) {
        return cartItemRepository.findAllById(id);
    }

    public List<CartItemDto> getCartItemsByUserId(UUID userId) {
        return cartItemRepository.findAllByUserId(userId)
                .stream()
                .filter(cart -> cart.getUserId().equals(userId))
                .map(CartItemDto::new)
                .toList();
    }

    public boolean cartItemExists(UUID id) {
        return cartItemRepository.existsById(id);
    }

    public void addItemToCart(
            UUID userId,
            UUID itemId
    ) {
        CartItemEntity cartItemEntity = new CartItemEntity(
                UUID.randomUUID(),
                userId,
                itemId,
                LocalDateTime.now()
        );
        cartItemRepository.save(cartItemEntity);
    }

    public void deleteCartItem(UUID userId, UUID itemId) {
        CartItemEntity cartItemEntity = cartItemRepository.findByUserIdAndItemId(userId, itemId)
                .orElseThrow(() -> new AssetDoesNotExistException("Cart item"));
        cartItemRepository.delete(cartItemEntity);
    }
}
