package org.venkat.reactor.literx;

import org.venkat.reactor.literx.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Part04Transform {

    //Capitalize the user username, firstname and lastname
    Mono<User> capitalizeOne(Mono<User> mono) {
        return mono.map(user -> new User(user.getUsername().toUpperCase(),
                        user.getFirstname().toUpperCase(),
                        user.getLastname().toUpperCase()
                )
        );
    }


    //Capitalize the users username, firstName and lastName
    Flux<User> capitalizeMany(Flux<User> flux) {
        return flux.map(user -> new User(user.getUsername().toUpperCase(),
                user.getFirstname().toUpperCase(),
                user.getLastname().toUpperCase()
        ));
    }

    //========================================================================================

    // Capitalize the users username, firstName and lastName using #asyncCapitalizeUser
    Flux<User> asyncCapitalizeMany(Flux<User> flux) {
        return flux.flatMap(this::asyncCapitalizeUser);
    }

    private Mono<User> asyncCapitalizeUser(User u) {
        return Mono.just(new User(u.getUsername().toUpperCase(), u.getFirstname().toUpperCase(), u.getLastname().toUpperCase()));
    }

}
