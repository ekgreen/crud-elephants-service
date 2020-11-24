package com.goodboy.edu.crud.elephants.service.api.services.user;

import com.goodboy.edu.crud.elephants.service.api.vo.User;
import lombok.NonNull;

import java.util.Optional;

public interface UserRepository {

    long create(@NonNull User user);

    Optional<User> findByUsername(String username);

}
