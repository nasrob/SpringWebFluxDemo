package com.nasboukehil.webflux.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nasboukehil.webflux.model.Student;
import com.nasboukehil.webflux.repository.StudentMongoRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api")
public class StudentWebFluxController {

	@Autowired
	private StudentMongoRepository studentMongoRepository;
	
	@GetMapping("/getStudent/{rollNo}")
	public Mono<ResponseEntity<Student>> getStudent(@PathVariable("rollNo") Integer rollNo) {
		Mono<Student> studentMonoObj = studentMongoRepository.findByRollNo(rollNo);
		return studentMonoObj.map(student -> ResponseEntity.ok(student))
								.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/getAllStudents")
	public Flux<Student> getAllStudent() {
		Flux<Student> allStudents = studentMongoRepository.findAll();
		return allStudents;
	}
	
	
}
