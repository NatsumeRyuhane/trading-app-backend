package com.flag3.tradingappbackend.item;

import com.flag3.tradingappbackend.db.dto.ItemDto;
import com.flag3.tradingappbackend.db.entity.ItemEntity;
import com.flag3.tradingappbackend.db.entity.UserEntity;
import com.flag3.tradingappbackend.db.enums.ItemStatusEnum;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
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

    @GetMapping("/search")
    public List<ItemDto> searchItems(@RequestParam String name) {
        return itemService.searchItemsByName(name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postItem(
            @AuthenticationPrincipal UserEntity user,
            String name,
            double price,
            String description,
            List<MultipartFile> media,
            ItemStatusEnum status,
            String address
    ) {
        itemService.createItem(
                user.getId(),
                name,
                price,
                description,
                media,
                status,
                address
        );
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@AuthenticationPrincipal UserEntity user, @PathVariable String id) {
        itemService.deleteItem(user.getId(), UUID.fromString(id));
    }

}
