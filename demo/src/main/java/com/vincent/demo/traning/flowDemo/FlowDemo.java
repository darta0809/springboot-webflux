package com.vincent.demo.traning.flowDemo;

import java.util.Scanner;
import java.util.concurrent.Flow;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.SubmissionPublisher;

public class FlowDemo {

    public void flowBase() {
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
        Flow.Subscriber<String> subscriber = new Flow.Subscriber<>() {
            private Flow.Subscription subscription;

            @Override
            public void onSubscribe(Subscription subscription) {
                this.subscription = subscription;
                // 向數據發布者請求一個數據
                this.subscription.request(1);
            }

            @Override
            public void onNext(String item) {
                System.out.println("接收到 publisher 發來的消息了 : " + item);
                // 接收完成後，可以繼續接收或者不接收
                // this.subscription.cancel();
                this.subscription.request(1);
            }

            @Override
            public void onError(Throwable throwable) {
                // 出現異常，就會來到這裡，此時直接取消訂閱即可
                this.subscription.cancel();
            }

            @Override
            public void onComplete() {
                // 發布者的所有數據都被接收，並且發布者已經關閉
                System.out.println("數據接收完畢");
            }
        };
        // 配置發布者與訂閱者
        publisher.subscribe(subscriber);
        for (int i = 0; i < 5; i++) {
            // 發送數據
            publisher.submit("hello " + i);
        }
        publisher.close();
        new Scanner(System.in).next();
    }
}
