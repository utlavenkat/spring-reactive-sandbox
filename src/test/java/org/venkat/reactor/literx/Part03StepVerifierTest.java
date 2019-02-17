package org.venkat.reactor.literx;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.venkat.reactor.literx.domain.User;

import java.time.Duration;

import static org.junit.Assert.*;

public class Part03StepVerifierTest {
    private Part03StepVerifier workshop = new Part03StepVerifier();

//========================================================================================

    @Test
    public void expectElementsThenComplete() {
        workshop.expectFooBarComplete(Flux.just("foo", "bar"));
    }

//========================================================================================

    @Test
    public void expect2ElementsThenError() {
        workshop.expectFooBarError(Flux.just("foo", "bar").concatWith(Mono.error(new RuntimeException())));
    }

//========================================================================================

    @Test
    public void expectElementsWithThenComplete() {
        workshop.expectSkylerJesseComplete(Flux.just(new User("swhite", null, null), new User("jpinkman", null, null)));
    }

//========================================================================================

    @Test
    public void count() {
        workshop.expect10Elements(Flux.interval(Duration.ofSeconds(1)).take(10));
    }

//========================================================================================

    @Test
    public void countWithVirtualTime() {
        workshop.expect3600Elements(() -> Flux.interval(Duration.ofSeconds(1)).take(36));
    }
}