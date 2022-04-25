package com.vincent.demo;

import com.vincent.demo.traning.WebFlux;
import com.vincent.demo.traning.flowDemo.FlowDemo;
import com.vincent.demo.traning.flowDemo.FlowDemoBackpressure;
import com.vincent.demo.traning.flowDemo.FlowDemoProcessor;
import com.vincent.demo.traning.lambda.Lambda_Base;
import com.vincent.demo.traning.lambda.Lambda_Fuction;
import com.vincent.demo.traning.lambda.Lambda_Predicate;
import com.vincent.demo.traning.lambda.Lambda_UnaryOperAtor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

        Lambda_Base lambda = new Lambda_Base();
        WebFlux webFlux = new WebFlux();
        Lambda_Fuction lambda_fuction = new Lambda_Fuction();
        Lambda_UnaryOperAtor lambda_unaryOperAtor = new Lambda_UnaryOperAtor();
        Lambda_Predicate lambda_predicate = new Lambda_Predicate();
        FlowDemo flowDemo = new FlowDemo();
        FlowDemoBackpressure flowDemoBackpressure = new FlowDemoBackpressure();
        FlowDemoProcessor flowDemoProcessor = new FlowDemoProcessor();
        // StreamBase streamBase = new StreamBase();
        // streamBase.collectBase();

        flowDemoProcessor.flowProcessorBase();
    }
}
