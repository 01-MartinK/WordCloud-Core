package com.component.core;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class WordCloudCoreComponent {

	@Autowired
	private Flyway flyway;

	public void migrateDatabase() {
		flyway.migrate();
	}
	public static void main(String[] args) {
		SpringApplication.run(WordCloudCoreComponent.class, args);
	}
}
