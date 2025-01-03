package com.example.todoprojectdevelop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TodoProjectDevelopApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoProjectDevelopApplication.class, args);
    }

}
