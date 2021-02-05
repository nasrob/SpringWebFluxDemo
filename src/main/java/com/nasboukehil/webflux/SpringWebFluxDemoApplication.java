package com.nasboukehil.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
public class SpringWebFluxDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWebFluxDemoApplication.class, args);
	}

}
