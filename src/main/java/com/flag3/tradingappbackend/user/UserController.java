package com.flag3.tradingappbackend.user;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest body) {
        userService.register(body.username(), body.password(), body.firstName(), body.lastName(), body.address());
    }

    @PostMapping("/login")
    public String  login(@RequestBody LoginRequest body) {
        return userService.login(body.username(), body.password());
    }

}
