package com.flag3.tradingappbackend.db;

import com.flag3.tradingappbackend.db.entity.ItemEntity;
import com.flag3.tradingappbackend.db.enums.ItemStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
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
}
