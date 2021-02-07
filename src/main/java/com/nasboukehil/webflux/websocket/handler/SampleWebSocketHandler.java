package com.nasboukehil.webflux.websocket.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nasboukehil.webflux.model.Student;
import com.nasboukehil.webflux.repository.StudentMongoRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class SampleWebSocketHandler implements WebSocketHandler{

	private ObjectMapper objMapper = new ObjectMapper();
	
	@Autowired
	StudentMongoRepository studentMongoRepository;
	
	@Override
	public Mono<Void> handle(WebSocketSession webSocketSession) {
		Flux<Student> allStudentSource = studentMongoRepository.findAll();
		System.out.println(" ****** Incoming Messages ****** ");
		webSocketSession.receive().subscribe(System.out::println);
		
		System.out.println(" ****** Sending Data ****** ");
		
		return webSocketSession.send(allStudentSource.map(student -> {
			return writeValueAsString(student);
		}).map(webSocketSession::textMessage));
	}

	private Object writeValueAsString(Object obj) throws JsonProcessingException {
		try {
			return objMapper.writeValueAsString(obj);
		} catch (JsonParseException ex) {
			ex.printStackTrace();
		}
		return "No Data";
	}
}
