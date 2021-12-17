package io.github.emfsilva.restfull;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class RestfullApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfullApplication.class, args);

		/*
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(16);
		String result = bCryptPasswordEncoder.encode("admin123");
		System.out.println("My hash " + result);*/

	}

}
