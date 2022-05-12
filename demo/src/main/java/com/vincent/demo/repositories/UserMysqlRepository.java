package com.vincent.demo.repositories;

import com.vincent.demo.entity.UserMysql;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMysqlRepository extends ReactiveCrudRepository<UserMysql, Long> {

}
