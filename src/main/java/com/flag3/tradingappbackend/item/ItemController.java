package com.flag3.tradingappbackend.item;

import com.flag3.tradingappbackend.db.entity.ItemEntity;
import com.flag3.tradingappbackend.db.entity.UserEntity;
import com.flag3.tradingappbackend.db.enums.ItemStatusEnum;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/items")
@AllArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/")
    public List<ItemEntity> getAllItemsAvailable() {
        return itemService.getAllItems()
                .stream()
                .filter(item -> item.getStatus().equals(ItemStatusEnum.AVAILABLE))
                .toList();
    }

    // TODO: Implement this endpoint after the authentication module is implemented
    public List<ItemEntity> getItemListing(@AuthenticationPrincipal UserEntity user) {
        return itemService.getAllItems()
                .stream()
                .filter(item -> item.getUserId().equals(user.getId()))
                .toList();
    }

    @GetMapping("/search")
    public List<ItemEntity> searchItems(String query) {
        return itemService.searchItemsByName(query);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void postItem(@AuthenticationPrincipal UserEntity user,  @RequestBody ItemPublishRequest body) {
        itemService.createItem(
                user.getId(),
                body.name(),
                body.price(),
                body.description(),
                body.mediaUrls(),
                body.status(),
                body.address()
        );
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@AuthenticationPrincipal UserEntity user, @PathVariable String id) {
        itemService.deleteItem(user.getId(), UUID.fromString(id));
    }
}
