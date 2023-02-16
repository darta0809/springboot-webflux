package com.vincent.demo.handler;

import com.vincent.demo.entity.Person;
import com.vincent.demo.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static java.lang.Long.parseLong;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.ServerResponse.notFound;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
@RequiredArgsConstructor
public class PersonHandler {

    private final PersonRepository personRepository;

    public Mono<ServerResponse> addPerson(ServerRequest serverRequest) {
        return ok().contentType(APPLICATION_JSON)
                .body(personRepository.saveAll(serverRequest.bodyToMono(Person.class)), Person.class);
    }

    public Mono<ServerResponse> deletePerson(ServerRequest serverRequest) {
        return personRepository.findById(parseLong(serverRequest.pathVariable("id")))
                .flatMap(p -> personRepository.delete(p).then(ok().build()))
                .switchIfEmpty(notFound().build());
    }

    public Mono<ServerResponse> getAllPerson(ServerRequest serverRequest) {
        return ok().contentType(APPLICATION_JSON)
                .body(personRepository.findAll(), Person.class);
    }
}
