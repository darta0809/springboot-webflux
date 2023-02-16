package com.vincent.demo.controller;

import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class MonoController {

    /**
     * RESTful
     */
    @GetMapping("/hello")
    public String hello() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        String helloStr = getHelloStr();
        if (stopWatch.isRunning()) {
            stopWatch.stop();
        }
        System.out.println(stopWatch.getTotalTimeSeconds());
        return helloStr;
    }

    /**
     * Spring WebFlux
     * Mono：實現釋出者 Publisher ，並返回 0 或 1 個元素
     */
    @GetMapping("/hello2")
    public Mono<String> hello2() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Mono<String> hello2 = Mono.fromSupplier(this::getHelloStr);
        if (stopWatch.isRunning()) {
            stopWatch.stop();
        }
        System.out.println(stopWatch.getTotalTimeSeconds());
        return hello2;
    }

    private String getHelloStr() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello";
    }
}
