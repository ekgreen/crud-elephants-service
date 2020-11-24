package com.goodboy.edu.crud.elephants.service.impl.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AboutController {

    @GetMapping("/about")
    public String about() {
        return "Elephants service for postman examples";
    }
}
