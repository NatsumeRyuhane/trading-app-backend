package com.flag3.tradingappbackend.item;

import com.flag3.tradingappbackend.db.ItemRepository;
import com.flag3.tradingappbackend.db.dto.ItemDto;
import com.flag3.tradingappbackend.db.entity.ItemEntity;
import com.flag3.tradingappbackend.db.entity.UserEntity;
import com.flag3.tradingappbackend.db.enums.ItemCategoryEnum;
import com.flag3.tradingappbackend.db.enums.ItemStatusEnum;
import com.flag3.tradingappbackend.exceptions.AssetDoesNotExistException;
import com.flag3.tradingappbackend.exceptions.ItemOperationUnauthorizedException;
import com.flag3.tradingappbackend.storage.MediaStorageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final MediaStorageService mediaStorageService;

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
                .filter(item -> item.getStatus() == ItemStatusEnum.AVAILABLE)
                .map(ItemDto::new)
                .toList();
    }
    public List<ItemDto> searchItemsByCategory(ItemCategoryEnum category) {
        return itemRepository.findAllByCategory(category)
                .stream()
                .filter(item -> item.getStatus() == ItemStatusEnum.AVAILABLE)
                .map(ItemDto::new)
                .toList();
    }

    public void createItem(
            UUID userId,
            String name,
            double price,
            String description,
            List<MultipartFile> media,
            ItemStatusEnum status,
            ItemCategoryEnum category,
            String address
    ) {
        List<String> mediaUrls = media.parallelStream()
                .filter(file -> !file.isEmpty())
                .map(mediaStorageService::upload)
                .toList();

        ItemEntity itemEntity = new ItemEntity(
                UUID.randomUUID(),
                userId,
                name,
                price,
                description,
                mediaUrls,
                status,
                category,
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

    public void updateItemStatus(UUID id, ItemStatusEnum status) {
        itemRepository.updateStatus(id, status.name());
    }

}
