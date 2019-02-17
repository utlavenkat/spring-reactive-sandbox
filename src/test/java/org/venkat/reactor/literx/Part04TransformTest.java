package org.venkat.reactor.literx;

import org.junit.Test;
import org.venkat.reactor.literx.domain.User;
import org.venkat.reactor.literx.repository.ReactiveRepository;
import org.venkat.reactor.literx.repository.ReactiveUserRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Part04TransformTest {

    private Part04Transform workshop = new Part04Transform();
    private ReactiveRepository<User> repository = new ReactiveUserRepository();

//========================================================================================

    @Test
    public void transformMono() {
        Mono<User> mono = repository.findFirst();
        StepVerifier.create(workshop.capitalizeOne(mono))
                .expectNext(new User("SWHITE", "SKYLER", "WHITE"))
                .verifyComplete();
    }

//========================================================================================

    @Test
    public void transformFlux() {
        Flux<User> flux = repository.findAll();
        StepVerifier.create(workshop.capitalizeMany(flux))
                .expectNext(
                        new User("SWHITE", "SKYLER", "WHITE"),
                        new User("JPINKMAN", "JESSE", "PINKMAN"),
                        new User("WWHITE", "WALTER", "WHITE"),
                        new User("SGOODMAN", "SAUL", "GOODMAN"))
                .verifyComplete();
    }

//========================================================================================

    @Test
    public void  asyncTransformFlux() {
        Flux<User> flux = repository.findAll();
        StepVerifier.create(workshop.asyncCapitalizeMany(flux))
                .expectNext(
                        new User("SWHITE", "SKYLER", "WHITE"),
                        new User("JPINKMAN", "JESSE", "PINKMAN"),
                        new User("WWHITE", "WALTER", "WHITE"),
                        new User("SGOODMAN", "SAUL", "GOODMAN"))
                .verifyComplete();
    }
}