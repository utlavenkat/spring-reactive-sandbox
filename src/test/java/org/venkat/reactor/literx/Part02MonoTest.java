package org.venkat.reactor.literx;

import org.junit.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;

import static org.junit.Assert.*;

public class Part02MonoTest {
    private Part02Mono workshop = new Part02Mono();

//========================================================================================

    @Test
    public void empty() {
        Mono<String> mono = workshop.emptyMono();
        StepVerifier.create(mono)
                .verifyComplete();
    }

//========================================================================================

    @Test
    public void noSignal() {
        Mono<String> mono = workshop.monoWithNoSignal();
        StepVerifier
                .create(mono)
                .expectSubscription()
                .expectNoEvent(Duration.ofSeconds(1))
                .thenCancel()
                .verify();
    }

//========================================================================================

    @Test
    public void fromValue() {
        Mono<String> mono = workshop.fooMono();
        StepVerifier.create(mono)
                .expectNext("foo")
                .verifyComplete();
    }

//========================================================================================

    @Test
    public void error() {
        Mono<String> mono = workshop.errorMono();
        StepVerifier.create(mono)
                .verifyError(IllegalStateException.class);
    }


}