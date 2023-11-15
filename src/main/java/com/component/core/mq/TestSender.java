package com.component.core.mq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class TestSender {

    @Autowired
    private final RabbitTemplate template;

    @Autowired
    private Queue queue;

    public TestSender(RabbitTemplate rabbitTemplate) {
        this.template = rabbitTemplate;
    }

    public void send() {
        String message = "hello world";
        this.template.convertAndSend("wordcloud", "wordcloud.worker.baz", message);
        System.out.println(" [x] Sent '" + message + "'");
    }
}
