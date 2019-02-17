package org.venkat.spring5.reactive;

import org.junit.Test;
import reactor.core.publisher.Mono;

public class MonoTest {

    @Test
    public void firstMono() {
        Mono.just("A").log().subscribe(s -> System.out.println("Message Produced by Mono  ::  "+s));
    }

    @Test
    public void monoWithDoOn() {
        Mono.just("A")
                .log().
                doOnSubscribe(subscription -> System.out.println("Subscribed::"+subscription))
                .doOnRequest(request -> System.out.println("Requested::"+request))
                .doOnSuccess(complete -> System.out.println("Complete::"+complete))
                .subscribe(System.out::println);
    }

    /**
     * Empty mono is used to emulate void methods to let the subscribers the mono is completed.
     */
    @Test
    public void emptyMono() {
        Mono.empty().log().subscribe(System.out::println);
    }

    @Test
    public void emptyCompleteConsumer() {
        Mono.empty().log().subscribe(System.out::println,null,() -> System.out.println("Done"));
    }

    @Test
    public void errorRuntimeExceptionMono() {
        Mono.error(new Exception("Test Exception")).log().subscribe(System.out::println,
                throwable -> System.out.println("Exception occurred::"+ throwable.getMessage()));
    }
}
