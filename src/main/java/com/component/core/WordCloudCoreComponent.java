package com.component.core;

import com.component.core.mq.Runner;
import com.component.core.mq.TestSender;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class WordCloudCoreComponent {

	public static void main(String[] args) {
		SpringApplication.run(WordCloudCoreComponent.class, args);
	}
}
