package com.example.zlxpractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class ZlxPracticeApplication {

    @RequestMapping("/")
    public String home() {
        return "Hello, Spring Boot!";
    }

    public static void main(String[] args) {
        SpringApplication.run(ZlxPracticeApplication.class, args);
    }

}
