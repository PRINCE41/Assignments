package com.apica.UserMngService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class UserMngServiceApplication {

	public static void main(String[] args) {
		log.info("Entering main");
		SpringApplication.run(UserMngServiceApplication.class, args);
		log.info("Exiting main");
	}

}
