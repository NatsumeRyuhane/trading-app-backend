package com.flag3.tradingappbackend.cart;

import com.flag3.tradingappbackend.db.CartRepository;
import com.flag3.tradingappbackend.db.entity.CartEntity;
import com.flag3.tradingappbackend.db.entity.ItemEntity;
import com.flag3.tradingappbackend.exceptions.CartOperationUnauthorizedException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    public List<CartEntity> getAllCarts() {
        return cartRepository.findAll();
    }

    public Optional<CartEntity> getCartById(UUID id) {
        return cartRepository.findById(id);
    }

    public boolean cartExists(UUID id) {
        return cartRepository.existsById(id);
    }

    public void createCart(
            UUID userId,
            UUID cartId,
            List<ItemEntity> items
    ) {
        CartEntity cartEntity = new CartEntity(
                UUID.randomUUID(),
                userId,
                cartId,
                items
        );
        cartRepository.save(cartEntity);
    }


    public void deleteCart(UUID userId, UUID cartId) {
        CartEntity cartEntity = cartRepository.findById(cartId).get();
        if (cartEntity.getUserId().equals(userId)) {
            cartRepository.deleteById(userId);
        } else {
            throw new CartOperationUnauthorizedException("Unable to delete cart: User does not own cart");
        }
    }
}
