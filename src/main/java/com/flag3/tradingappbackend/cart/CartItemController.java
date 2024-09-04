package com.flag3.tradingappbackend.cart;

import com.flag3.tradingappbackend.db.dto.CartItemDto;
import com.flag3.tradingappbackend.db.entity.CartItemEntity;
import com.flag3.tradingappbackend.db.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cart")
@AllArgsConstructor
public class CartItemController {
    private final CartItemService cartItemService;

//    @GetMapping
//    public List<CartItemEntity> getAllCartItems() {
//        return cartItemService.getAllCartItems();
//    }

    @GetMapping
    public List<CartItemDto> getMyCartItems(@AuthenticationPrincipal UserEntity user) {
        return cartItemService.getCartItemsByUserId(user.getId());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postCart(@AuthenticationPrincipal UserEntity user, @RequestParam UUID itemId) {
        cartItemService.addItemToCart(
                user.getId(),
                itemId
        );
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@AuthenticationPrincipal UserEntity user, @PathVariable UUID id) {
        cartItemService.deleteCartItem(user.getId(), id);
    }
}
