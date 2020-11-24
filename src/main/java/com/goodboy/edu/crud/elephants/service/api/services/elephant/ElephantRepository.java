package com.goodboy.edu.crud.elephants.service.api.services.elephant;

import com.goodboy.edu.crud.elephants.service.api.vo.Elephant;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public interface ElephantRepository {

    long create(Elephant elephant);

    Optional<Elephant> get(long id);

    List<Elephant> getAll();

    boolean remove(long id);

    void update(long id, Consumer<Elephant> consumer);

    boolean clear();
}
