package com.example.jobservice.controller;

import com.example.jobservice.dto.JobDto;
import com.example.jobservice.service.JobService;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("job")
public class JobController {

	@Autowired
	JobService jobService;

	@GetMapping("all")
	public Flux<JobDto> all() {
		return this.jobService.findAllJobs();
	}

	@GetMapping("search")
	public Flux<JobDto> all(@RequestParam Set<String> skills) {
		return this.jobService.findAllJobsBySkillsIn(skills);
	}

	@PostMapping
	public Mono<JobDto> save(@RequestBody Mono<JobDto> mono) {
		return this.jobService.saveJob(mono);
	}
}
