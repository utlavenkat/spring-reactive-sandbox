package org.venkat.reactor.literx;

import org.junit.Test;
import org.venkat.reactor.literx.domain.User;
import org.venkat.reactor.literx.repository.ReactiveRepository;
import org.venkat.reactor.literx.repository.ReactiveUserRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.Assert.*;

public class Part05MergeTest {

    private Part05Merge workshop = new Part05Merge();

    private final static User MARIE = new User("mschrader", "Marie", "Schrader");
    private final static User MIKE = new User("mehrmantraut", "Mike", "Ehrmantraut");

    private ReactiveRepository<User> repositoryWithDelay = new ReactiveUserRepository(500);
    private ReactiveRepository<User> repository          = new ReactiveUserRepository(MARIE, MIKE);

//========================================================================================

    @Test
    public void mergeWithInterleave() {
        Flux<User> flux = workshop.mergeFluxWithInterleave(repositoryWithDelay.findAll(), repository.findAll());
        StepVerifier.create(flux)
                .expectNext(MARIE, MIKE, User.SKYLER, User.JESSE, User.WALTER, User.SAUL)
                .verifyComplete();
    }

//========================================================================================

    @Test
    public void mergeWithNoInterleave() {
        Flux<User> flux = workshop.mergeFluxWithNoInterleave(repositoryWithDelay.findAll(), repository.findAll());
        StepVerifier.create(flux)
                .expectNext(User.SKYLER, User.JESSE, User.WALTER, User.SAUL, MARIE, MIKE)
                .verifyComplete();
    }

//========================================================================================

    @Test
    public void multipleMonoToFlux() {
        Mono<User> skylerMono = repositoryWithDelay.findFirst();
        Mono<User> marieMono = repository.findFirst();
        Flux<User> flux = workshop.createFluxFromMultipleMono(skylerMono, marieMono);
        StepVerifier.create(flux)
                .expectNext(User.SKYLER, MARIE)
                .verifyComplete();
    }
}