package com.vincent.demo.controller;

import com.vincent.demo.entity.User;
import com.vincent.demo.repositories.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserDao userDao;

    @PostMapping("/")
    public Mono<User> addUser(@RequestBody User user) {
        return userDao.save(user);
    }

    @GetMapping("/")
    public Flux<User> getAll() {
        return userDao.findAll();
    }

    @GetMapping(value = "/stream/all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> streamGetAll() {
        return userDao.findAll();
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteUser(@PathVariable String id) {
        return userDao.findById(id)
                .flatMap(user -> userDao.delete(user).then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
                .defaultIfEmpty(new ResponseEntity(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/")
    public Mono<ResponseEntity<User>> updateUser(@RequestBody User user) {
        return userDao.findById(user.getId())
                .flatMap(u -> userDao.save(user))
                .map(u -> new ResponseEntity<User>(u, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/byname")
    public Flux<User> getUserByName(String name) {
        return userDao.findUserByUsernameContaining(name);
    }
}
