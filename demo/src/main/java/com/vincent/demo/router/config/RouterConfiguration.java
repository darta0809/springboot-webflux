package com.vincent.demo.router.config;

import com.vincent.demo.handler.PersonHandler;
import com.vincent.demo.handler.UserMysqlHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfiguration {

    @Bean
    RouterFunction<ServerResponse> personRouterFunction(PersonHandler personHandler) {
        return RouterFunctions
                .nest(RequestPredicates.path("/person"),
                        RouterFunctions.route(RequestPredicates.POST("/"), personHandler::addPerson)
                                .andRoute(RequestPredicates.GET("/"), personHandler::getAllPerson)
                                .andRoute(RequestPredicates.DELETE("/{id}"), personHandler::deletePerson));
    }

    @Bean
    RouterFunction<ServerResponse> userRouterFunction(UserMysqlHandler userMysqlHandler) {
        return RouterFunctions.nest(RequestPredicates.path("/userMysql"),
                RouterFunctions.route(RequestPredicates.GET("/"), userMysqlHandler::getAllUsers)
                        .andRoute(RequestPredicates.POST("/"), userMysqlHandler::addUser)
                        .andRoute(RequestPredicates.DELETE("/{id}"), userMysqlHandler::deleteUser));
    }
}
