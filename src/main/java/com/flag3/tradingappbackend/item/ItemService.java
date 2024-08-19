package com.flag3.tradingappbackend.item;

import com.flag3.tradingappbackend.db.ItemRepository;
import com.flag3.tradingappbackend.db.entity.ItemEntity;
import com.flag3.tradingappbackend.db.enums.ItemStatusEnum;
import com.flag3.tradingappbackend.exceptions.ItemOperationUnauthorizedException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public List<ItemEntity> getAllItems() {
        return itemRepository.findAll();
    }

    public Optional<ItemEntity> getItemById(UUID id) {
        return itemRepository.findById(id);
    }

    public boolean itemExists(UUID id) {
        return itemRepository.existsById(id);
    }

    public List<ItemEntity> searchItemsByName(String query) {
        return itemRepository.findAllByNameContaining(query);
    }

    public void createItem(
            UUID userId,
            String name,
            double price,
            String description,
            String mediaUrls,
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
                LocalDateTime.now(),
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
