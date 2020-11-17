package com.mati.vaii;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class VaiiApplication {

	public static void main(String[] args) {
		SpringApplication.run(VaiiApplication.class, args);
	}

}
