package com.goodboy.edu.crud.elephants.service.impl.controller;

import com.goodboy.edu.crud.elephants.service.api.services.user.UserRepository;
import com.goodboy.edu.crud.elephants.service.api.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/v1")
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class AuthorizationController {

    private final UserRepository repository;
    private final BCryptPasswordEncoder encoder;

    @PostMapping("/sign-up")
    public String signUp(@RequestBody User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        repository.create(user);
        return "Success singed-up user [" + user.getUsername() + "]";
    }
}
