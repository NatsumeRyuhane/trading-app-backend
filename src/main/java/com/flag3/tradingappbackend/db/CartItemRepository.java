package com.flag3.tradingappbackend.db;

import com.flag3.tradingappbackend.db.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemEntity, UUID> {

    Optional<CartItemEntity> findById(UUID id);

    List<CartItemEntity> findByUserId(UUID userId);

    List<CartItemEntity> findByItemId(UUID itemId);

    List<CartItemEntity> findByCreatedTime(UUID CreatedAt);
}
