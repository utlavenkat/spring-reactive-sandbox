package org.venkat.spring5.reactive;

import org.junit.Test;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FluxTest {

    @Test
    public void firstTest() {
        Flux.just("A","B","C").log().subscribe(System.out::println);
    }

    @Test
    public void fluxWithIterable() {
        List<String> names = new ArrayList<>(3);
        names.add("Venkat");
        names.add("Lakshmi");
        names.add("Hanshitha");

        Flux.fromIterable(names).log().subscribe(System.out::println);
    }

    @Test
    public void fluxUsingRange() {

        Flux.range(1,10).log().subscribe(System.out::println);
    }

    @Test
    public void fluxFromInterval() throws Exception {

        Flux.interval(Duration.ofSeconds(2)).log().subscribe();
        Thread.sleep(10000);
    }

    @Test
    public void fluxFromIntervalAndTake() throws Exception {

        Flux.interval(Duration.ofSeconds(2)).log().take(2).subscribe();
        Thread.sleep(10000);
    }

    @Test
    public void fluxRequest() {
        Flux.range(1,10).log()
                .subscribe(null,
                        null,
                        null,
                        subscription -> subscription.request(3)
                );
    }

    @Test
    public void fluxLimitRate() {
        Flux.range(1,10).log().limitRate(3).subscribe();
    }

    @Test
    public void fluxCustomSubscriber() {
        Flux.range(1,10)
                .log()
                .subscribe(new BaseSubscriber<Integer>() {
                    int elementsToProcess = 3;
                    int counter = 0;

                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        System.out.println("Subscribed");
                        request(elementsToProcess);
                    }

                    @Override
                    protected void hookOnNext(Integer value) {
                        counter++;
                        if (counter == elementsToProcess) {
                            counter = 0;
                            Random r = new Random();
                            elementsToProcess = r.ints(1,4).findFirst().getAsInt();
                            request(elementsToProcess);
                        }
                    }

                    @Override
                    protected void hookOnComplete() {
                        System.out.println("Completed!!");
                    }

                    @Override
                    protected void hookOnError(Throwable throwable) {
                        System.out.println("Error Occurred::"+throwable.getMessage());
                    }

                    @Override
                    protected void hookOnCancel() {
                        System.out.println("Subscription Cancelled");
                    }
                });
    }

}
