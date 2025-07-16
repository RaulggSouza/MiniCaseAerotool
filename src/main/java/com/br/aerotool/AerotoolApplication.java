package com.br.aerotool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AerotoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(AerotoolApplication.class, args);
	}

}
