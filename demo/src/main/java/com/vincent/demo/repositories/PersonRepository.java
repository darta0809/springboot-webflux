package com.vincent.demo.repositories;

import com.vincent.demo.entity.Person;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
public interface PersonRepository extends ReactiveMongoRepository<Person,Long> {

}
