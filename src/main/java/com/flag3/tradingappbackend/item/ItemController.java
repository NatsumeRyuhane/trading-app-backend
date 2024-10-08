package com.flag3.tradingappbackend.item;

import com.flag3.tradingappbackend.db.dto.ItemDto;
import com.flag3.tradingappbackend.db.entity.UserEntity;
import com.flag3.tradingappbackend.db.enums.ItemCategoryEnum;
import com.flag3.tradingappbackend.db.enums.ItemStatusEnum;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/items")
@AllArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public List<ItemDto> getAllAvailableItems() {
        return itemService.getAllAvailableItems();
    }

    @GetMapping("/mine")
    public List<ItemDto> getAllItemsOfCurrentUser(@AuthenticationPrincipal UserEntity user) {
        return itemService.getAllItemsOfUser(user.getId());
    }

    @GetMapping("/{itemId}")
    public ItemDto getItemById(@PathVariable UUID itemId) {
        return itemService.getItemById(itemId);
    }

    @GetMapping("/search")
    public List<ItemDto> searchItems(@RequestParam String name) {
        return itemService.searchItemsByName(name);
    }

    @GetMapping("/category")
    public List<ItemDto> searchItemsByCategory(@RequestParam ItemCategoryEnum category){return itemService.searchItemsByCategory( category);}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postItem(
            @AuthenticationPrincipal UserEntity user,
            String name,
            double price,
            String description,
            List<MultipartFile> media,
            ItemStatusEnum status,
            ItemCategoryEnum category,
            String address
    ) {
        itemService.createItem(
                user.getId(),
                name,
                price,
                description,
                media,
                status,
                category,
                address
        );
    }

    @PatchMapping("/{id}")
    public void updateItemStatus(@PathVariable UUID id, @RequestParam ItemStatusEnum status) {
        itemService.updateItemStatus(id, status);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@AuthenticationPrincipal UserEntity user, @PathVariable String id) {
        itemService.deleteItem(user.getId(), UUID.fromString(id));
    }

}
