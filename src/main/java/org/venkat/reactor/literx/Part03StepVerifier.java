package org.venkat.reactor.literx;

import org.venkat.reactor.literx.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.function.Supplier;

/**
 * @author utlavenk
 * @see <a href="http://projectreactor.io/docs/test/release/api/reactor/test/StepVerifier.html">StepVerifier Javadoc</a>
 */
public class Part03StepVerifier {

//========================================================================================

    // Use StepVerifier to check that the flux parameter emits "foo" and "bar" elements then completes successfully.
    void expectFooBarComplete(Flux<String> flux) {
        StepVerifier.create(flux).expectNext("foo","bar").expectComplete().verify();//.verifyComplete().verify();
    }

//========================================================================================

    // Use StepVerifier to check that the flux parameter emits "foo" and "bar" elements then a RuntimeException error.
    void expectFooBarError(Flux<String> flux) {
        StepVerifier.create(flux).expectNext("foo","bar").expectError(RuntimeException.class).verify();
    }

//========================================================================================

    // Use StepVerifier to check that the flux parameter emits a User with "swhite"username
    // and another one with "jpinkman" then completes successfully.
    void expectSkylerJesseComplete(Flux<User> flux) {

       StepVerifier.create(flux).expectNextMatches(user->user.getUsername().equalsIgnoreCase(User.SKYLER.getUsername()))
               .expectNextMatches(user -> user.getUsername().equalsIgnoreCase(User.JESSE.getUsername()))
               .expectComplete().verify();
    }

//========================================================================================

    // Expect 10 elements then complete and notice how long the test takes.
    void expect10Elements(Flux<Long> flux) {
       Duration duration = StepVerifier.create(flux).expectNextCount(10).expectComplete().verify();
        System.out.println("Time Taken"+duration.getSeconds());
    }

//========================================================================================

    // TODO Expect 3600 elements at intervals of 1 second, and verify quicker than 3600s
    // by manipulating virtual time thanks to StepVerifier#withVirtualTime, notice how long the test takes
    void expect3600Elements(Supplier<Flux<Long>> supplier) {
       /* Duration duration1 = StepVerifier.create(supplier.get()).expectNextCount(36).expectComplete().verify();
        System.out.println("Time Taken initially::"+duration1.getSeconds());*/
        Duration duration2 = StepVerifier.withVirtualTime(supplier).thenAwait(Duration.ofSeconds(360)).expectNextCount(36).verifyComplete();
        System.out.println("Time taken with VirtualTime::"+duration2.getSeconds());
    }
}

