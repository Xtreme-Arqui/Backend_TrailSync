package com.example.backend_TrailSync;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BackendTrailSyncApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendTrailSyncApplication.class, args);
	}

}
