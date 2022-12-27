package com.example.jobservice.service;

import com.example.jobservice.dto.JobDto;
import com.example.jobservice.entity.Job;
import com.example.jobservice.repository.JobRepository;
import com.example.jobservice.util.EntityDtoUtil;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class JobService {
	@Autowired
	JobRepository jobRepository;

	public Flux<JobDto> findAllJobs() {
		return jobRepository
				.findAll()
				.map(EntityDtoUtil::toDto);
	}

	public Flux<JobDto> findAllJobsBySkillsIn(Set<String> skills) {
		return jobRepository
				.findBySkillsIn(skills)
				.map(EntityDtoUtil::toDto);
	}

	public Mono<JobDto> saveJob(Mono<JobDto> jobDtoMono){
		return jobDtoMono
				.map(EntityDtoUtil::toEntity)
				.flatMap(job -> jobRepository.save(job))
				// Alternate to above .flatMap(jobRepository::save)
				.map(EntityDtoUtil::toDto);
	}
}
