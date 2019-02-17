package org.venkat.reactor.literx;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * @author utlavenk
 * @see  <a href="http://projectreactor.io/docs/core/release/api/reactor/core/publisher/Flux.html">Flux Javadoc</a>
 */
public class Part01Flux {

    Flux<String> emptyFlux() {
        return Flux.empty();
    }

    Flux<String> fooBarFluxFromValues() {
        return Flux.just("foo","bar");
    }

    Flux<String> fooBarFluxFromList() {
        List<String> messages = new ArrayList<>(2);
        messages.add("foo");
        messages.add("bar");
        return Flux.fromIterable(messages);
    }

    Flux<String> errorFlux() {
        return Flux.error(new IllegalStateException("Error occurred"));
    }

    // Create a Flux that emits increasing values from 0 to 9 each 100ms
    Flux<Long> counter() {
        return Flux.interval(Duration.ofMillis(100)).take(10);
    }

    // Create a Flux that emits increasing values each 100ms duration
    void interval() throws Exception {
        Flux.interval(Duration.ofMillis(100)).subscribe(System.out::println);
        Thread.sleep(4000);
    }


}
