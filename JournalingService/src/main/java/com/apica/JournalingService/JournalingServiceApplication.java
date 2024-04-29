package com.apica.JournalingService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class JournalingServiceApplication {

	public static void main(String[] args) {
		log.info("Entering main");
		SpringApplication.run(JournalingServiceApplication.class, args);
		log.info("Exiting main");
	}

}
