package com.example.candidateservice.client;

import com.example.candidateservice.dto.JobDto;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class JobClient {

	private final WebClient webClient;

	public JobClient(@Value("${job.service.url}") String baseUrl) {
		this.webClient = WebClient.builder()
				.baseUrl(baseUrl)
				.build();
	}

	public Mono<List<JobDto>> getRecommendedJobsBySkills(Set<String> skills) {
		System.out.println("Skills: " + skills);
		return webClient
				.get()
				.uri(u -> u.path("search").queryParam("skills", skills).build())
				//.uri("search?skills=", skills)
				.retrieve()
				.bodyToFlux(JobDto.class)
				.collectList()
				.onErrorReturn(Collections.emptyList());
	}
}
