package com.vincent.demo.router.config;

import com.vincent.demo.handler.PersonHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfiguration {
    @Bean
    RouterFunction<ServerResponse> personRouter(PersonHandler personHandler) {
        return RouterFunctions
                .nest(RequestPredicates.path("/person"),
                        RouterFunctions.route(RequestPredicates.POST("/"), personHandler::addPerson)
                                .andRoute(RequestPredicates.GET("/"), personHandler::getAllPerson)
                                .andRoute(RequestPredicates.DELETE("/{id}"), personHandler::deletePerson));
    }
}
