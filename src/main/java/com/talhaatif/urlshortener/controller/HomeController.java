package com.talhaatif.urlshortener.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "Home Controller",
        description =  "Welcome to URL shortening service!!"
)
@RestController
public class HomeController {

    @GetMapping("/")
    public String health(){
        return "Let's use URL shortening";
    }
}
