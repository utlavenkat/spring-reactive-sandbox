package org.venkat.reactor.literx;

import org.venkat.reactor.literx.domain.User;
import org.venkat.reactor.literx.repository.ReactiveRepository;
import org.venkat.reactor.literx.repository.ReactiveUserRepository;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class Part06Request {

    private ReactiveRepository<User> repository = new ReactiveUserRepository();

//========================================================================================

    //Create a StepVerifier that initially requests all values and expect 4 values to be received
    StepVerifier requestAllExpectFour(Flux<User> flux) {
        return StepVerifier.create(flux).thenRequest(4L).thenCancel();
    }

//========================================================================================

    // TODO Create a StepVerifier that initially requests 1 value and expects User.SKYLER then requests another value and expects User.JESSE.
    StepVerifier requestOneExpectSkylerThenRequestOneExpectJesse(Flux<User> flux) {
        return StepVerifier.create(flux).expectNext(User.SKYLER).thenRequest(1).expectNext(User.JESSE).thenRequest(1).thenCancel();
    }

//========================================================================================

    // TODO Return a Flux with all users stored in the repository that prints automatically logs for all Reactive Streams signals
    Flux<User> fluxWithLog() {

        return Flux.from(repository.findAll());
    }

//========================================================================================

    // TODO Return a Flux with all users stored in the repository that prints "Starring:" on subscribe, "firstname lastname" for all values and "The end!" on complete
    Flux<User> fluxWithDoOnPrintln() {
        return Flux.from(repository.findAll().doOnSubscribe(subscription -> System.out.println("Starring"))
                .doOnNext(user -> System.out.println(user.getFirstname() +" " + user.getLastname()))
                .doOnComplete(() -> System.out.println("The end!"))
                        );
    }

}