package com.flag3.tradingappbackend.user;

import com.flag3.tradingappbackend.db.TransactionRepository;
import com.flag3.tradingappbackend.db.UserRepository;
import com.flag3.tradingappbackend.db.dto.TransactionDto;
import com.flag3.tradingappbackend.db.entity.TransactionEntity;
import com.flag3.tradingappbackend.db.entity.UserEntity;
import com.flag3.tradingappbackend.exceptions.UserAlreadyExistsException;
import com.flag3.tradingappbackend.security.JwtHandler;
import com.flag3.tradingappbackend.transaction.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtHandler jwtHandler;

    public UserEntity register(
            String username,
            String password,
            String firstName,
            String lastName,
            String address,
            String phoneNumber
    ) throws UserAlreadyExistsException {
        if (userRepository.existsByUsername(username)) {
            throw new UserAlreadyExistsException();
        }

        UserEntity userEntity = new UserEntity(
                UUID.randomUUID(),
                username,
                passwordEncoder.encode(password),
                firstName,
                lastName,
                address,
                phoneNumber
        );
        return userRepository.save(userEntity);
    }

    public String login(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        return jwtHandler.generateToken(username);
    }

    public RatingResponse getUserRating(UUID id) {
        List<TransactionEntity> transactionList = transactionRepository.findAllBySellerId(id);
        double total = 0.0;
        int numRatings = 0;
        for (TransactionEntity i : transactionList) {
            if (i.getBuyerToSellerRating() != null) {
                total += i.getBuyerToSellerRating();
                numRatings++;
            }
        }
        double avgRating = numRatings > 0 ? total / numRatings : -1;
        return new RatingResponse(avgRating, numRatings);
    }
}
