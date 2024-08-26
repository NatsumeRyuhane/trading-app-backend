package com.flag3.tradingappbackend.db;

import com.flag3.tradingappbackend.db.entity.CartEntity;
import com.flag3.tradingappbackend.db.entity.ItemEntity;
import com.flag3.tradingappbackend.db.enums.ItemStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, UUID> {

    Optional<CartEntity> findById(UUID id);

    List<CartEntity> findByUserId(UUID userId);

    List<CartEntity> findByCartId(UUID cartId);
}
