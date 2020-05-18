package com.example.springfinctiontest;

import java.util.function.Function;
import java.util.function.Supplier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@SpringBootApplication
@RestController
@RequestMapping("/")
public class SpringFinctionTestApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringFinctionTestApplication.class, args);
  }

  EmitterProcessor<String> proc = EmitterProcessor.create();

  @RequestMapping("/upperCase/{name}")
  public Mono<String> upperCase(@PathVariable String name) {
    proc.onNext(name);

    return Mono.just(name.toUpperCase());

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
