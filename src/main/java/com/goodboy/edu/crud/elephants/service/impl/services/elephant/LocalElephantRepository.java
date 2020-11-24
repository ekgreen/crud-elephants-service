package com.goodboy.edu.crud.elephants.service.impl.services.elephant;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.goodboy.edu.crud.elephants.service.api.services.elephant.ElephantRepository;
import com.goodboy.edu.crud.elephants.service.api.services.restrictions.RestrictionService;
import com.goodboy.edu.crud.elephants.service.api.vo.Elephant;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

import static java.util.stream.Collectors.toMap;

@Service
public class LocalElephantRepository implements ElephantRepository {

    private final Map<Long, Elephant> cached;
    private final AtomicLong sequence;

    @Autowired @SneakyThrows
    public LocalElephantRepository(ObjectMapper mapper) {
        this.sequence = new AtomicLong(0);
        this.cached = mapper.readValue(this.getClass().getClassLoader().getResourceAsStream("static/local-elephant.json"), new TypeReference<List<Elephant>>() {})
                .stream()
                .collect(toMap(
                        el -> getSequentialId(),
                        elephant -> elephant,
                        (el1, el2) -> {
                            throw new IllegalArgumentException();
                        },
                        ConcurrentHashMap::new
                ));
    }


    @Override
    public long create(Elephant elephant) {
        long sequentialId = getSequentialId();
        cached.put(sequentialId, elephant.setId(sequentialId));
        return sequentialId;
    }

    @Override
    public Optional<Elephant> get(long id) {
        return Optional.ofNullable(cached.get(id));
    }

    @Override
    public List<Elephant> getAll() {
        return new ArrayList<>(cached.values());
    }

    @Override
    public boolean remove(long id) {
        cached.remove(id);
        return true;
    }

    @Override
    public void update(long id, Consumer<Elephant> consumer) {
        get(id).ifPresent(elephant -> {
            consumer.accept(elephant);
            elephant.setId(id);
        });
    }

    @Override
    public boolean clear() {
        cached.clear();
        return true;
    }

    private long getSequentialId() {
        return sequence.getAndIncrement();
    }
}
