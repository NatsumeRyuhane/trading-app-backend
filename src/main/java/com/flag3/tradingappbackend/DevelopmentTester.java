package com.flag3.tradingappbackend;

import com.flag3.tradingappbackend.db.UserRepository;
import com.flag3.tradingappbackend.db.entity.UserEntity;
import com.flag3.tradingappbackend.user.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@AllArgsConstructor
public class DevelopmentTester implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(DevelopmentTester.class);

    private final UserService userService;

    private final UserRepository userRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        userRepository.save(new UserEntity(
                UUID.fromString("e18452c5-c2d0-492f-b53f-a62121adc466"),
                "stevenysy",
                "secret",
                "steven",
                "yi",
                "123 Main Street",
                true,
                LocalDateTime.now()
        ));
    }

}
