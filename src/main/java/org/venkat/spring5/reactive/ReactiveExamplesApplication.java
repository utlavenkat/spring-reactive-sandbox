package org.venkat.spring5.reactive;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.venkat.spring5.reactive.model.Product;
import org.venkat.spring5.reactive.repositories.ProductRepository;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class ReactiveExamplesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveExamplesApplication.class, args);
    }

    @Bean
    CommandLineRunner init(ProductRepository repository) {
        return args -> {
           Flux<Product> productFlux = Flux.just(
                    Product.builder().name("Big Latte").price(2.29).build(),
                    Product.builder().name("Big Decaf").price(2.49).build(),
                    Product.builder().name("Green Tea").price(1.99).build()
            ).flatMap(repository::save);

           productFlux.thenMany(repository.findAll()).subscribe(System.out::println);
        };
    }
}
