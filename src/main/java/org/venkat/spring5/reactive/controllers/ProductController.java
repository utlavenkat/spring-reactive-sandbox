package org.venkat.spring5.reactive.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.venkat.spring5.reactive.model.Product;
import org.venkat.spring5.reactive.repositories.ProductRepository;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Product>> getProductById(final @PathVariable("id") String id) {
       return productRepository.findById(id)
               .map(ResponseEntity::ok)
               .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
