package com.flag3.tradingappbackend.user;

import com.flag3.tradingappbackend.db.UserRepository;
import com.flag3.tradingappbackend.db.entity.UserEntity;
import com.flag3.tradingappbackend.exceptions.UserAlreadyExistsException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserEntity register(
            String username,
            String password,
            String firstName,
            String lastName,
            String address
    ) throws UserAlreadyExistsException {
        if (userRepository.existsByUsername(username)) {
            throw new UserAlreadyExistsException();
        }

        UserEntity userEntity = new UserEntity(
                UUID.randomUUID(),
                username,
                password,
                firstName,
                lastName,
                address,
                true,
                LocalDateTime.now()
        );
        return userRepository.save(userEntity);
    }

    public String login(String username, String password) {
        if (userRepository.existsByUsername(username)) {
            return "logged in";
        }
        return "not registered";
    }

}
