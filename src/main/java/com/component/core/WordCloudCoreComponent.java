package com.component.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class WordCloudCoreComponent {

	public static void main(String[] args) {
		SpringApplication.run(WordCloudCoreComponent.class, args);
	}
}
