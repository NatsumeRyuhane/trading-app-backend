package com.flag3.tradingappbackend.db;

import com.flag3.tradingappbackend.db.entity.ItemEntity;
import com.flag3.tradingappbackend.db.enums.ItemCategoryEnum;
import com.flag3.tradingappbackend.db.enums.ItemStatusEnum;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, UUID> {

    Optional<ItemEntity> findById(UUID id);

    List<ItemEntity> findAllByUserId(UUID userId);

    List<ItemEntity> findAllByNameContaining(String name);

    List<ItemEntity> findAllByStatus(ItemStatusEnum statusEnum);

    List<ItemEntity> findAllByCategory(ItemCategoryEnum category);

    @Modifying
    @Transactional
    @Query(value = "UPDATE items SET status = :status WHERE id = :id", nativeQuery = true)
    void updateStatus(UUID id, String status);

}
