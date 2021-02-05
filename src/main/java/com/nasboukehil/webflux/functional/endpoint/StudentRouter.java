package com.nasboukehil.webflux.functional.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class StudentRouter {

	@Autowired
	private StudentHandler studentHandler;
	
	@Bean
	RouterFunction<ServerResponse> returnStudent() {
		return RouterFunctions.route(RequestPredicates.GET("/api/f/getStudent/{rollNo}"),
															studentHandler::getStudent);
	}
	
	@Bean
	RouterFunction<ServerResponse> returnAllStudents() {
		return RouterFunctions.route(RequestPredicates.GET("/api/f/getAllStudents"), 
															studentHandler::getAllStudent);
	}
}
