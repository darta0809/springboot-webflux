package com.vincent.demo.handler;

import static java.lang.Long.parseLong;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.ServerResponse.notFound;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import com.vincent.demo.entity.Person;
import com.vincent.demo.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class PersonHandler {

    @Autowired
    PersonRepository personRepository;

    public Mono<ServerResponse> addPerson(ServerRequest serverRequest) {
        // return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
        //         .body(personRepository.saveAll(serverRequest.bodyToMono(Person.class)), Person.class);
        return ok().contentType(APPLICATION_JSON)
                .body(personRepository.saveAll(serverRequest.bodyToMono(Person.class)), Person.class);
    }

    public Mono<ServerResponse> deletePerson(ServerRequest serverRequest) {
        // return personRepository.findById(Long.parseLong(serverRequest.pathVariable("id")))
        //         .flatMap(p -> personRepository.delete(p).then(ok().build()))
        //         .switchIfEmpty(ServerResponse.notFound().build());
        return personRepository.findById(parseLong(serverRequest.pathVariable("id")))
                .flatMap(p -> personRepository.delete(p).then(ok().build()))
                .switchIfEmpty(notFound().build());
    }
    public Mono<ServerResponse> getAllPerson(ServerRequest serverRequest) {
        return ok().contentType(APPLICATION_JSON)
                .body(personRepository.findAll(), Person.class);
    }
}
