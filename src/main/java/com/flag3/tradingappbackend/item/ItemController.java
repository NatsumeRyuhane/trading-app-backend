package com.flag3.tradingappbackend.item;

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
@RequestMapping("/items")
@AllArgsConstructor
public class ItemController {
    private final ItemService itemService;

    // TODO: Replace hard-coded user with authed user
    private final UserEntity user = new UserEntity(UUID.fromString("e18452c5-c2d0-492f-b53f-a62121adc466"), "stevenysy", "secret", "steven", "yi", "123 Main Street", true, LocalDateTime.now());

    @GetMapping
    public List<ItemEntity> getAllItemsAvailable() {
        return itemService.getAllItems()
                .stream()
                .filter(item -> item.getStatus().equals(ItemStatusEnum.AVAILABLE))
                .toList();
    }

    // TODO: Implement this endpoint after the authentication module is implemented
    // TODO: Replace ItemEntity with ItemDTO
    @GetMapping("/mine")
    public List<ItemEntity> getItemListing() {
        return itemService.getAllItems()
                .stream()
                .filter(item -> item.getUserId().equals(user.getId()))
                .toList();
    }

    @GetMapping("/search")
    public List<ItemEntity> searchItems(@RequestParam String name) {
        return itemService.searchItemsByName(name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postItem(@RequestBody ItemPublishRequest body) {
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
    public void deleteItem(@PathVariable String id) {
        itemService.deleteItem(user.getId(), UUID.fromString(id));
    }
}
