package com.example.candidateservice.service;

import com.example.candidateservice.client.JobClient;
import com.example.candidateservice.dto.CandidateDetailsDto;
import com.example.candidateservice.dto.CandidateDto;
import com.example.candidateservice.repository.CandidateRepository;
import com.example.candidateservice.util.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CandidateService {

	@Autowired
	CandidateRepository candidateRepository;

	@Autowired
	JobClient jobClient;

	public Flux<CandidateDto> getAllCandidates() {
		return candidateRepository
				.findAll()
				.map(EntityDtoUtil::toDto);
	}

	// When we search by some candidate id then we will populate the list of recommended jobs for them
	public Mono<CandidateDetailsDto> getCandidate(String id) {
		return candidateRepository
				.findById(id)
				.map(EntityDtoUtil::toCandidateDetailsDto)
				.flatMap(candidateDetailsDto -> jobClient
						.getRecommendedJobsBySkills(candidateDetailsDto.getSkills()) // Mono<List<JobDto>>
						.map(jobList -> {
							candidateDetailsDto.setRecommendedJobs(jobList);
							return candidateDetailsDto;
						}));
	}

	public Mono<CandidateDto> saveCandidate(Mono<CandidateDto> candidateDtoMono) {
		return candidateDtoMono
				.map(EntityDtoUtil::toEntity)
				.flatMap(candidateRepository::save)
				.map(EntityDtoUtil::toDto);
	}
}
