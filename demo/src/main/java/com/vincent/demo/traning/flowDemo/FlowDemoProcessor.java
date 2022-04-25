package com.vincent.demo.traning.flowDemo;

import java.util.Scanner;
import java.util.concurrent.Flow;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.SubmissionPublisher;

public class FlowDemoProcessor {

    /*
     * Flow.Processor 可以像過濾器一樣，對數據進行預處理，
     * 數據從 publisher 出來之後，先進入 Flow.Processor 中進行預處理，然後再進入 Subscriber
     * */
    public void flowProcessorBase() {
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
        DataFilter dataFilter = new DataFilter();
        publisher.subscribe(dataFilter);

        Flow.Subscriber<String> subscriber = new Flow.Subscriber<>() {
            private Flow.Subscription subscription;

            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                this.subscription = subscription;
                // 向數據發布者請求一個數據
                this.subscription.request(1);
            }

            @Override
            public void onNext(String item) {
                System.out.println("接收到 publisher 發來的消息了：" + item);
                // 接收完成後，可以繼續接收或者不接收
                //this.subscription.cancel();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.subscription.request(1);
            }

            @Override
            public void onError(Throwable throwable) {
                // 出現異常，就會來到這個方法，此時直接取消訂閱即可
                this.subscription.cancel();
            }

            @Override
            public void onComplete() {
                // 發布者的所有數據都被接收，並且發布者已經關閉
                System.out.println("數據接收完畢");
            }
        };
        dataFilter.subscribe(subscriber);
        for (int i = 0; i < 500; i++) {
            System.out.println("發送消息 i--------->" + i);
            publisher.submit("hello: " + i);
        }
        //关闭发布者
        publisher.close();
        new Scanner(System.in).next();
    }
}

class DataFilter extends SubmissionPublisher<String> implements Flow.Processor<String, String> {

    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        this.subscription.request(1);
    }

    @Override
    public void onNext(String item) {
        this.submit("【這是一條被處理過的數據】" + item);
        this.subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        this.subscription.cancel();
    }

    @Override
    public void onComplete() {
        this.close();
    }
}