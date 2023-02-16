package com.vincent.demo.handler;

import com.vincent.demo.entity.UserMysql;
import com.vincent.demo.repositories.UserMysqlRepository;
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
public class UserMysqlHandler {

    private final UserMysqlRepository userMysqlRepository;

    public Mono<ServerResponse> getAllUsers(ServerRequest serverRequest) {
        return ok().contentType(APPLICATION_JSON)
                .body(userMysqlRepository.findAll(), UserMysql.class);
    }

    public Mono<ServerResponse> addUser(ServerRequest serverRequest) {
        return ok().contentType(APPLICATION_JSON)
                .body(userMysqlRepository.saveAll(serverRequest.bodyToMono(UserMysql.class)), UserMysql.class);
    }

    public Mono<ServerResponse> deleteUser(ServerRequest serverRequest) {
        return userMysqlRepository.findById(parseLong(serverRequest.pathVariable("id")))
                .flatMap(user -> userMysqlRepository.delete(user).then(ok().build()))
                .switchIfEmpty(notFound().build());
    }

}
