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
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

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
                "Steven",
                "Yi",
                "123 Main Street",
                "1234567890"
        ));

        userRepository.save(new UserEntity(
                joeyUuid,
                "jlau",
                passwordEncoder.encode("secret"),
                "Joey",
                "Lau",
                "123 Main Street",
                "0987654321"
        ));

        itemRepository.save(new ItemEntity(
                UUID.fromString("b6f961f3-dd4e-4ef2-8ab0-ae93a72608a0"),
                stevenUuid,
                "King-size bed",
                40.00,
                "Moving out after graduating, selling my beloved bed",
                List.of("https://images.unsplash.com/photo-1640003145136-f998284e11de?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTB8fG1hdHRyZXNzfGVufDB8fDB8fHww"),
                ItemStatusEnum.ONGOING_TRADE,
                ItemCategoryEnum.BEDDING,
                "123 Main Street"
        ));

        itemRepository.save(new ItemEntity(
                UUID.randomUUID(),
                stevenUuid,
                "Desktop Lamp",
                20,
                "Desktop lamp with a convenient USB socket",
                List.of("https://img5.su-cdn.com/cdn-cgi/image/width=750,height=750/mall/file/2022/09/21/854afde5554f54b4c55587c389ef04b9.jpg", "https://www.sofary.com/cdn/shop/files/4_586cc78d-5fb3-49e6-a881-5eace633b4d6.jpg?v=1683624701&width=1200"),
                ItemStatusEnum.AVAILABLE,
                ItemCategoryEnum.LIGHTING,
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
                "Stainless Steel Pot",
                30,
                "Brand new pot, selling because too small for my needs",
                List.of("https://cdn.sanity.io/images/fr9flhkd/main/70c81d91b0ff74420e527cd339f1a2216a73a83e-1800x1000.jpg?fm=webp&q=75&w=1280"),
                ItemStatusEnum.AVAILABLE,
                ItemCategoryEnum.KITCHEN,
                "123 Main Street"
        ));

        itemRepository.save(new ItemEntity(
                UUID.randomUUID(),
                stevenUuid,
                "Iron pot",
                15,
                "Great pot for cooking soup and pasta",
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
                "Comfy and portable chair, bought 2 months ago",
                List.of("https://images.unsplash.com/photo-1612372606404-0ab33e7187ee?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8Y2hhaXJ8ZW58MHx8MHx8fDA%3D"),
                ItemStatusEnum.AVAILABLE,
                ItemCategoryEnum.FURNITURE,
                "123 Main Street"
        ));

        itemRepository.save(new ItemEntity(
                UUID.randomUUID(),
                joeyUuid,
                "Frying Pan",
                50,
                "Brand new non-stick frying pan good for anything",
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
                "1-year-old air fryer, still in good shape",
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
                "Queen size mattress, clean and not too soft",
                List.of("https://images.unsplash.com/photo-1640003145136-f998284e11de?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTB8fG1hdHRyZXNzfGVufDB8fDB8fHww"),
                ItemStatusEnum.AVAILABLE,
                ItemCategoryEnum.BEDDING,
                "123 Main Street"
        ));

        itemRepository.save(new ItemEntity(
                UUID.randomUUID(),
                joeyUuid,
                "Wireless keyboard",
                50,
                "Logitech wireless keyboard, comes with extra batteries",
                List.of("https://images.unsplash.com/photo-1694405156884-dea1ffb40ede?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTF8fHdpcmVsZXNzJTIwa2V5Ym9hcmR8ZW58MHx8MHx8fDA%3D"),
                ItemStatusEnum.AVAILABLE,
                ItemCategoryEnum.ELECTRONICS,
                "123 Main Street"
        ));

        itemRepository.save(new ItemEntity(
                UUID.randomUUID(),
                joeyUuid,
                "Small Sofa",
                80,
                "Cozy sofa, perfect for the living room",
                List.of("https://images.unsplash.com/photo-1512212621149-107ffe572d2f?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8c29mYXxlbnwwfHwwfHx8MA%3D%3D"),
                ItemStatusEnum.AVAILABLE,
                ItemCategoryEnum.FURNITURE,
                "123 Main Street"
        ));

        itemRepository.save(new ItemEntity(
                UUID.randomUUID(),
                stevenUuid,
                "Home Shelf",
                35,
                "Shelf bought three months ago, great for organizing",
                List.of("https://i5.walmartimages.com/seo/Mainstays-5-Shelf-Bookcase-with-Adjustable-Shelves-White_4fc187dc-9c50-4dd8-a636-3e659ab74799.6f15f74ae9951e69d39329b5e161e35a.png?odnHeight=768&odnWidth=768&odnBg=FFFFFF"),
                ItemStatusEnum.AVAILABLE,
                ItemCategoryEnum.ORGANIZING,
                "123 Main Street"
        ));

    }

}
