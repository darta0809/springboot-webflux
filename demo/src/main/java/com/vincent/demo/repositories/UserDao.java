package com.vincent.demo.repositories;

import com.vincent.demo.entity.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import reactor.core.publisher.Flux;

@EnableMongoRepositories
public interface UserDao extends ReactiveMongoRepository<User, String> {

    Flux<User> findUserByUsernameContaining(String name);
}
