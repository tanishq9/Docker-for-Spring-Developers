package com.example.candidateservice.controller;

import com.example.candidateservice.dto.CandidateDetailsDto;
import com.example.candidateservice.dto.CandidateDto;
import com.example.candidateservice.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("candidate")
public class CandidateController {

	@Autowired
	CandidateService candidateService;

	@GetMapping("all")
	public Flux<CandidateDto> getAllCandidates() {
		return candidateService.getAllCandidates();
	}

	@GetMapping("{id}")
	public Mono<CandidateDetailsDto> getCandidate(@PathVariable String id) {
		return candidateService.getCandidate(id);
	}

	@PostMapping
	public Mono<CandidateDto> saveCandidate(@RequestBody Mono<CandidateDto> candidateDtoMono) {
		return candidateService.saveCandidate(candidateDtoMono);
	}
}
