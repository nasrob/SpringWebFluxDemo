package com.nasboukehil.webflux.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.nasboukehil.webflux.model.Student;

import reactor.core.publisher.Mono;

public interface StudentMongoRepository extends ReactiveMongoRepository<Student, String> {

	public Mono<Student> findByRollNo(Integer rollNo);
	public Mono<Student> findByName(String name);
}
