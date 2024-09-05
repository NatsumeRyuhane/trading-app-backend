package com.flag3.tradingappbackend.db.dto;

import com.flag3.tradingappbackend.db.entity.UserEntity;

import java.util.UUID;

public record UserDto(
        UUID id,
        String username,
        String firstName,
        String lastName,
        String address,
        String phoneNumber
) {

    public UserDto(UserEntity userEntity) {
        this(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getAddress(),
                userEntity.getPhoneNumber()
        );
    }

}
