package com.example.jobservice.repository;

import com.example.jobservice.entity.Job;
import java.util.Set;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface JobRepository extends ReactiveCrudRepository<Job, String> {
	Flux<Job> findBySkillsIn(Set<String> skills);
}
