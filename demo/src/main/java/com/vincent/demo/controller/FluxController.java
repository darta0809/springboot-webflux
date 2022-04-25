package com.vincent.demo.controller;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class FluxController {

    /**
     * Flux：實現釋出者 Publisher，並返回 N 個元素
     * text/event-stream 伺服器向瀏覽器推送訊息的一種方案，這種方案和我們所熟知的 WebSocket 有一些差別
     */
    @GetMapping(value = "/flux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> flux() {
        Flux<String> flux = Flux.fromArray(new String[]{"javaboy", "itboyhub", "www.javaboy.org", "itboyhub.com"}).map(s -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "my->data->" + s;
        });
        return flux;
    }
}
