package org.venkat.reactor.literx;

import org.junit.Test;
import org.venkat.reactor.literx.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.Assert.*;

public class Part07ErrorsTest {

    private Part07Errors workshop = new Part07Errors();

//========================================================================================

    @Test
    public void monoWithValueInsteadOfError() {
        Mono<User> mono = workshop.betterCallSaulForBogusMono(Mono.error(new IllegalStateException()));
        StepVerifier.create(mono)
                .expectNext(User.SAUL)
                .verifyComplete();

        mono = workshop.betterCallSaulForBogusMono(Mono.just(User.SKYLER));
        StepVerifier.create(mono)
                .expectNext(User.SKYLER)
                .verifyComplete();
    }

//========================================================================================

    @Test
    public void fluxWithValueInsteadOfError() {
        Flux<User> flux = workshop.betterCallSaulAndJesseForBogusFlux(Flux.error(new IllegalStateException()));
        StepVerifier.create(flux)
                .expectNext(User.SAUL, User.JESSE)
                .verifyComplete();

        flux = workshop.betterCallSaulAndJesseForBogusFlux(Flux.just(User.SKYLER, User.WALTER));
        StepVerifier.create(flux)
                .expectNext(User.SKYLER, User.WALTER)
                .verifyComplete();
    }

//========================================================================================

    @Test
    public void handleCheckedExceptions() {
        Flux<User> flux = workshop.capitalizeMany(Flux.just(User.SAUL, User.JESSE));

        StepVerifier.create(flux)
                .verifyError(Part07Errors.GetOutOfHereException.class);
    }


}