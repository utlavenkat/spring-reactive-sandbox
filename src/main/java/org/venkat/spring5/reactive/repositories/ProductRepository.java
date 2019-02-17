package org.venkat.spring5.reactive.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.venkat.spring5.reactive.model.Product;

public interface ProductRepository extends ReactiveMongoRepository<Product,String> {

}
