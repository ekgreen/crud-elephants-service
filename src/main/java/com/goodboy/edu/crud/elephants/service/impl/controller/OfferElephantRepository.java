package com.goodboy.edu.crud.elephants.service.impl.controller;

import com.goodboy.edu.crud.elephants.service.api.services.elephant.ElephantRepository;
import com.goodboy.edu.crud.elephants.service.api.services.elephant.ElephantService;
import com.goodboy.edu.crud.elephants.service.api.vo.Elephant;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/elephants/v1/offer")
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class OfferElephantRepository {

    private final ElephantService service;

    @GetMapping("/get")
    public List<Elephant> getOffer(@NonNull Authentication authentication){
        return service.getElephantOfferByUserId(authentication.getName());
    }

}
