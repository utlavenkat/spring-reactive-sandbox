package org.venkat.spring5.reactive;

import org.junit.Test;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class OperatorsTest {

    @Test
    public void map() {
        Flux.range(1,10).log().map(i -> i*10 ).subscribe(System.out::println);
    }

    @Test
    public void flatMap() {
        Flux.range(1,10).log().flatMap(i -> Flux.range(i*10 ,2)).subscribe(System.out::println);
    }

    @Test
    public void concat() throws Exception {
      Flux<Integer> oneToFive =  Flux.range(1,5).delayElements(Duration.ofMillis(200));
      Flux<Integer> sixToTen =  Flux.range(6,5).delayElements(Duration.ofMillis(400));
      Flux.concat(oneToFive,sixToTen).log().subscribe(System.out::println);
      Thread.sleep(5000);
    }

    @Test
    public void merge() throws Exception {
        Flux<Integer> oneToFive =  Flux.range(1,5).delayElements(Duration.ofMillis(200));
        Flux<Integer> sixToTen =  Flux.range(6,5).delayElements(Duration.ofMillis(400));
        Flux.merge(oneToFive,sixToTen).log().subscribe(System.out::println);
        Thread.sleep(5000);
    }

    @Test
    public void zip() {
        Flux<Integer> oneToFive =  Flux.range(1,5);//.delayElements(Duration.ofMillis(200));
        Flux<Integer> sixToTen =  Flux.range(6,5);//.delayElements(Duration.ofMillis(400));
        Flux.zip(oneToFive,sixToTen,(item1,item2)->item1 +","+item2).log().subscribe(System.out::println);
        //Thread.sleep(5000);
    }
}
