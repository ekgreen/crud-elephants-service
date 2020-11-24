package com.goodboy.edu.crud.elephants.service.impl.services.user;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.goodboy.edu.crud.elephants.service.api.services.user.UserRepository;
import com.goodboy.edu.crud.elephants.service.api.vo.User;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import static java.util.stream.Collectors.toMap;

@Service
public class LocalUserRepository implements UserRepository {

    private final Map<String, User> cached;
    private final AtomicLong sequence;

    @Autowired @SneakyThrows
    public LocalUserRepository(ObjectMapper mapper, BCryptPasswordEncoder encoder) {
        this.sequence = new AtomicLong(0);
        this.cached = mapper.readValue(this.getClass().getClassLoader().getResourceAsStream("static/local-users.json"), new TypeReference<List<User>>() {})
                .stream()
                .collect(toMap(
                        User::getUsername,
                        elephant -> elephant
                                .setId(getSequentialId())
                                .setPassword(encoder.encode(elephant.getPassword()))
                        ,
                        (el1, el2) -> {
                            throw new IllegalArgumentException();
                        },
                        ConcurrentHashMap::new
                ));
    }


    @Override
    public long create(@NonNull User user) {
        final String username = user.getUsername();

        if (cached.containsKey(username)) {
            return cached.get(username).getId();
        }

        final long sequentialId = getSequentialId();
        cached.put(username, user.setId(sequentialId));
        return sequentialId;
    }

    @Override
    public Optional<User> findByUsername(@NonNull String username) {
        return Optional.ofNullable(cached.get(username));
    }

    private long getSequentialId() {
        return sequence.getAndIncrement();
    }
}
