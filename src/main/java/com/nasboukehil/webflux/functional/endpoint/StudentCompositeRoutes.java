package com.nasboukehil.webflux.functional.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.nasboukehil.webflux.model.Student;
import com.nasboukehil.webflux.repository.StudentMongoRepository;

@Configuration
public class StudentCompositeRoutes {

	@Autowired
	private StudentMongoRepository studentMongoRepository;
	
	RouterFunction<ServerResponse> compositeRoutes() {
		RouterFunction<ServerResponse> studentResponse = RouterFunctions.
															route(RequestPredicates.GET("/api/f/composite/getStudent/{rollNo}"), 
																	serverRequest -> {
																		int rollNo = getInt(serverRequest.pathVariable("rollNo"));
																		return ServerResponse.ok()
																					.body(studentMongoRepository.findByRollNo(rollNo),
																							Student.class);
																	})
														.and(
															RouterFunctions.
															route(RequestPredicates.GET("/api/f/composite/getAllStudent"),
																	serverRequest ->
																		ServerResponse.ok()
																			.body(studentMongoRepository.findAll(), Student.class)
																	
																	)
															);
		return studentResponse;
	}

	private int getInt(String intStr) {
		int returnVal = 0;
		if (intStr != null && !intStr.isEmpty()) {
			try {
				returnVal = Integer.parseInt(intStr);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		return returnVal;
	}
	
	
}