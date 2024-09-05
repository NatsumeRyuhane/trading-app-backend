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
        final UUID stevenUuid = UUID.fromString("e18452c5-c2d0-492f-b53f-a62121adc466");
        final UUID joeyUuid = UUID.fromString("a85aa07b-736c-47c5-8ea7-4b2762c7efc6");

        userRepository.save(new UserEntity(
                stevenUuid,
                "stevenysy",
                passwordEncoder.encode("secret"),
                "steven",
                "yi",
                "123 Main Street",
                "1234567890"
        ));

        userRepository.save(new UserEntity(
                joeyUuid,
                "jlau",
                passwordEncoder.encode("secret"),
                "joey",
                "lau",
                "123 Main Street",
                "0987654321"
        ));

        itemRepository.save(new ItemEntity(
                UUID.fromString("b6f961f3-dd4e-4ef2-8ab0-ae93a72608a0"),
                stevenUuid,
                "King-size bed",
                40.00,
                "My bed",
                List.of("https://images.unsplash.com/photo-1640003145136-f998284e11de?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTB8fG1hdHRyZXNzfGVufDB8fDB8fHww"),
                ItemStatusEnum.ONGOING_TRADE,
                ItemCategoryEnum.BEDDING,
                "123 Main Street"
        ));

        transactionRepository.save(new TransactionEntity(
                UUID.fromString("8f993165-108b-4f85-a732-a66ab77a2c32"),
                UUID.fromString("b6f961f3-dd4e-4ef2-8ab0-ae93a72608a0"),
                joeyUuid,
                stevenUuid,
                TransactionStatusEnum.IN_PROGRESS
        ));

        itemRepository.save(new ItemEntity(
                UUID.randomUUID(),
                stevenUuid,
                "Pot 1",
                30,
                "Brand new pot",
                List.of("https://via.placeholder.com/225x166"),
                ItemStatusEnum.AVAILABLE,
                ItemCategoryEnum.KITCHEN,
                "123 Main Street"
        ));

        itemRepository.save(new ItemEntity(
                UUID.randomUUID(),
                stevenUuid,
                "Pot 2",
                35,
                "Brand new pot",
                List.of("https://images.unsplash.com/photo-1556911820-1238441ed1a7?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTZ8fHBvdHxlbnwwfHwwfHx8MA%3D%3D"),
                ItemStatusEnum.UNPUBLISHED,
                ItemCategoryEnum.KITCHEN,
                "123 Main Street"
        ));

        itemRepository.save(new ItemEntity(
                UUID.randomUUID(),
                stevenUuid,
                "Chair",
                49.9,
                "Brand new chair",
                List.of("https://images.unsplash.com/photo-1612372606404-0ab33e7187ee?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8Y2hhaXJ8ZW58MHx8MHx8fDA%3D"),
                ItemStatusEnum.AVAILABLE,
                ItemCategoryEnum.FURNITURE,
                "123 Main Street"
        ));

        itemRepository.save(new ItemEntity(
                UUID.randomUUID(),
                joeyUuid,
                "Pan",
                50,
                "Brand new pan",
                List.of("https://plus.unsplash.com/premium_photo-1672419800149-d04c372c5113?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NXx8cGFufGVufDB8fDB8fHww"),
                ItemStatusEnum.AVAILABLE,
                ItemCategoryEnum.KITCHEN,
                "123 Main Street"
        ));

        itemRepository.save(new ItemEntity(
                UUID.randomUUID(),
                joeyUuid,
                "Air Fryer",
                100,
                "Brand new air fryer",
                List.of("https://plus.unsplash.com/premium_photo-1672192166833-c8ae84e5e127?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8YWlyJTIwZnJ5ZXJ8ZW58MHx8MHx8fDA%3D"),
                ItemStatusEnum.AVAILABLE,
                ItemCategoryEnum.KITCHEN,
                "123 Main Street"
        ));

        itemRepository.save(new ItemEntity(
                UUID.randomUUID(),
                joeyUuid,
                "Used mattress",
                50,
                "Used mattress",
                List.of("https://images.unsplash.com/photo-1640003145136-f998284e11de?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTB8fG1hdHRyZXNzfGVufDB8fDB8fHww"),
                ItemStatusEnum.AVAILABLE,
                ItemCategoryEnum.BEDDING,
                "123 Main Street"
        ));

        itemRepository.save(new ItemEntity(
                UUID.randomUUID(),
                joeyUuid,
                "Wireless keyboard",
                90,
                "New wireless keyboard",
                List.of("https://images.unsplash.com/photo-1694405156884-dea1ffb40ede?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTF8fHdpcmVsZXNzJTIwa2V5Ym9hcmR8ZW58MHx8MHx8fDA%3D"),
                ItemStatusEnum.AVAILABLE,
                ItemCategoryEnum.ELECTRONICS,
                "123 Main Street"
        ));

        itemRepository.save(new ItemEntity(
                UUID.randomUUID(),
                joeyUuid,
                "Sofa",
                150,
                "New sofa",
                List.of("https://images.unsplash.com/photo-1512212621149-107ffe572d2f?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8c29mYXxlbnwwfHwwfHx8MA%3D%3D"),
                ItemStatusEnum.AVAILABLE,
                ItemCategoryEnum.FURNITURE,
                "123 Main Street"
        ));

    }

}
