package com.deepfx.serviceserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class DeepFXApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeepFXApplication.class, args);
	}

}
