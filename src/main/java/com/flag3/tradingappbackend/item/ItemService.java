package com.flag3.tradingappbackend.item;

import com.flag3.tradingappbackend.db.ItemRepository;
import com.flag3.tradingappbackend.db.dto.ItemDto;
import com.flag3.tradingappbackend.db.entity.ItemEntity;
import com.flag3.tradingappbackend.db.entity.UserEntity;
import com.flag3.tradingappbackend.db.enums.ItemStatusEnum;
import com.flag3.tradingappbackend.exceptions.AssetDoesNotExistException;
import com.flag3.tradingappbackend.exceptions.ItemOperationUnauthorizedException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public List<ItemDto> getAllItems() {
        return itemRepository.findAll()
                .stream()
                .map(ItemDto::new)
                .toList();
    }

    public List<ItemDto> getAllAvailableItems() {
        return itemRepository.findAllByStatus(ItemStatusEnum.AVAILABLE)
                .stream()
                .map(ItemDto::new)
                .toList();
    }

    public List<ItemDto> getAllItemsOfUser(UUID userId) {
        return itemRepository.findAllByUserId(userId)
                .stream()
                .map(ItemDto::new)
                .toList();
    }

    public ItemDto getItemById(UUID id) {
        ItemEntity itemEntity = itemRepository.findById(id)
                .orElseThrow(() -> new AssetDoesNotExistException("Item"));
        return new ItemDto(itemEntity);
    }

    public boolean itemExists(UUID id) {
        return itemRepository.existsById(id);
    }

    public List<ItemDto> searchItemsByName(String query) {
        return itemRepository.findAllByNameContaining(query)
                .stream()
                .map(ItemDto::new)
                .toList();
    }

    public void createItem(
            UUID userId,
            String name,
            double price,
            String description,
            List<String> mediaUrls,
            ItemStatusEnum status,
            String address
    ) {
        // TODO: deal with potential request duplication from front end
        ItemEntity itemEntity = new ItemEntity(
                UUID.randomUUID(),
                userId,
                name,
                price,
                description,
                mediaUrls,
                status,
                address
        );
        itemRepository.save(itemEntity);
    }

    public void deleteItem(UUID userId, UUID itemId) {
        ItemEntity itemEntity = itemRepository.findById(itemId).get();
        if (itemEntity.getUserId().equals(userId)) {
            itemRepository.deleteById(itemId);
        } else {
            throw new ItemOperationUnauthorizedException("Unable to delete item: User does not own item");
        }
    }

}
