package com.luluyang.twmovietheater;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {
    @GetMapping ("/hello/")
    public String home() {
        return "Hello world!";
    }
}
