package com.flag3.tradingappbackend.db;

import com.flag3.tradingappbackend.db.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemEntity, UUID> {

    Optional<CartItemEntity> findAllById(UUID id);

    List<CartItemEntity> findAllByUserId(UUID userId);

    List<CartItemEntity> findAllByItemId(UUID itemId);

    List<CartItemEntity> findAllByCreatedAt(LocalDateTime createdAt);
}
