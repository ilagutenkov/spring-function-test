package com.example.springfinctiontest;

import java.util.function.Function;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringFinctionTestApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringFinctionTestApplication.class, args);
  }


  @Bean
  public Function<String, String> upperCase() {
    return String::toUpperCase;
  }

}
