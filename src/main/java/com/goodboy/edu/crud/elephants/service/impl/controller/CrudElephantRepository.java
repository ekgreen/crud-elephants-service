package com.goodboy.edu.crud.elephants.service.impl.controller;

import com.goodboy.edu.crud.elephants.service.api.services.elephant.ElephantRepository;
import com.goodboy.edu.crud.elephants.service.api.vo.Elephant;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/elephants/v1/crud")
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class CrudElephantRepository {

    private final ElephantRepository repository;

    @PutMapping("/create")
    public Elephant create(@RequestBody Elephant elephant){
        repository.create(elephant);
        return elephant;
    }

    @GetMapping("/get/{id}")
    public Elephant get(@PathVariable("id") Long id) {
        return repository.get(id).orElse(null);
    }

    @GetMapping("/get")
    public Elephant getByParam(@RequestParam("id") Long id) {
        return repository.get(id).orElse(null);
    }

    @GetMapping("/get/all")
    public List<Elephant> getAll() {
        return repository.getAll();
    }

    @DeleteMapping("/remove/{id}")
    public boolean remove(@PathVariable("id") Long id) {
        return repository.remove(id);
    }

    @PostMapping("/update")
    public Elephant update(@RequestBody Elephant elephant){
        repository.update(elephant.getId(), (el) -> {
            if(elephant.getAge() != null)
                el.setAge(el.getAge());
            if(elephant.getName() != null)
                el.setName(el.getName());
            if(elephant.getType() != null)
                el.setType(el.getType());
            if(elephant.getTrunkLength() != null)
                el.setTrunkLength(el.getTrunkLength());
        });
        return repository.get(elephant.getId()).orElse(null);
    }
}
