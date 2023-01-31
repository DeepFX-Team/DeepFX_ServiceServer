package com.deepfx.serviceserver;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@OpenAPIDefinition(
		servers = {
				@Server(url = "http://localhost:4000", description = "Local Test Server"),
				@Server(url = "https://www.ljhhosting.com", description = "Service Server")
		})
@SpringBootApplication
public class DeepFXApplication {
	static {
		System.setProperty("com.amazonaws.sdk.disableEc2Metadata", "true");
	}

	public static void main(String[] args) {
		SpringApplication.run(DeepFXApplication.class, args);
	}

}
