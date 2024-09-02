package com.flag3.tradingappbackend;

import com.flag3.tradingappbackend.db.ItemRepository;
import com.flag3.tradingappbackend.db.TransactionRepository;
import com.flag3.tradingappbackend.db.UserRepository;
import com.flag3.tradingappbackend.db.entity.ItemEntity;
import com.flag3.tradingappbackend.db.entity.TransactionEntity;
import com.flag3.tradingappbackend.db.entity.UserEntity;
import com.flag3.tradingappbackend.db.enums.ItemCategoryEnum;
import com.flag3.tradingappbackend.db.enums.ItemStatusEnum;
import com.flag3.tradingappbackend.db.enums.TransactionStatusEnum;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class DevelopmentTester implements ApplicationRunner {

    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final PasswordEncoder passwordEncoder;
    private final TransactionRepository transactionRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        userRepository.save(new UserEntity(
                UUID.fromString("e18452c5-c2d0-492f-b53f-a62121adc466"),
                "stevenysy",
                passwordEncoder.encode("secret"),
                "steven",
                "yi",
                "123 Main Street",
                true,
                LocalDateTime.now()
        ));

        userRepository.save(new UserEntity(
                UUID.fromString("a85aa07b-736c-47c5-8ea7-4b2762c7efc6"),
                "jlau",
                passwordEncoder.encode("secret"),
                "joey",
                "lau",
                "123 Main Street",
                true,
                LocalDateTime.now()
        ));

        itemRepository.save(new ItemEntity(
                UUID.fromString("b6f961f3-dd4e-4ef2-8ab0-ae93a72608a0"),
                UUID.fromString("e18452c5-c2d0-492f-b53f-a62121adc466"),
                "King-size bed",
                40.00,
                "My bed",
                List.of("img1.jpeg"),
                ItemStatusEnum.SOLD,
                ItemCategoryEnum.BEDDING,
                "123 Main Street"
        ));

        transactionRepository.save(new TransactionEntity(
                UUID.fromString("8f993165-108b-4f85-a732-a66ab77a2c32"),
                UUID.fromString("b6f961f3-dd4e-4ef2-8ab0-ae93a72608a0"),
                UUID.fromString("a85aa07b-736c-47c5-8ea7-4b2762c7efc6"),
                UUID.fromString("e18452c5-c2d0-492f-b53f-a62121adc466"),
                TransactionStatusEnum.PENDING
        ));

    }

}
