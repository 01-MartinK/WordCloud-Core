package com.component.core.mq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class Runner implements CommandLineRunner {

    public static Runner runner;
    private final RabbitTemplate rabbitTemplate;
    private final Receiver receiver;

    public Runner(Receiver receiver, RabbitTemplate rabbitTemplate) {
        this.receiver = receiver;
        this.rabbitTemplate = rabbitTemplate;
        runner = this;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend("wordcloud", "wordcloud.worker.receive", "Hello from RabbitMQ!");
    }

    public void sendMessage(String msg){
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend("wordcloud", "wordcloud.worker.receive", msg);
    }
}
