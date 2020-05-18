package com.example.springfinctiontest;

import java.util.function.Function;
import java.util.function.Supplier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;

@Slf4j
@SpringBootApplication
@RestController
public class SpringFinctionTestApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringFinctionTestApplication.class, args);
  }

  EmitterProcessor<String> proc = EmitterProcessor.create();

  @Bean
  public Function<Flux<String>, Flux<String>> upperCase() {
    return message -> {
      return Flux.from(message.doOnNext(msg -> log.info("hello")))
          .map(item -> item.toUpperCase())
          .doOnNext(item->proc.onNext(item));
    };
  }

    @Bean
    public Supplier<Flux<String>> lowerCase() {
      return () -> {
        log.info("hi");
        return Flux.from(proc)
            .doOnNext(i->log.info("process"))
            .doOnSubscribe(s->log.info("subscribed"));
      };

    }

  }
