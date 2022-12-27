package com.example.candidateservice;

import com.example.candidateservice.dto.CandidateDto;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@SpringBootTest
@AutoConfigureWebTestClient
class CandidateServiceApplicationTests extends BaseTest {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void testGetAllCandidates() {
		webTestClient
				.get()
				.uri("/candidate/all")
				.exchange()
				.expectStatus().is2xxSuccessful()
				.expectBody()
				.jsonPath("$").isArray();
	}

	@Test
	void testGetCandidate() {
		String result = webTestClient
				.get()
				.uri("/candidate/{id}", 1)
				.exchange()
				.expectStatus().is2xxSuccessful()
				.expectBody()
				.jsonPath("$.name").isNotEmpty()
				.jsonPath("$.recommendedJobs").isNotEmpty()
				.returnResult()
				.toString();

		System.out.println(result);
	}

	@Test
	void testGetCandidateNoMatch() {
		String result = webTestClient
				.get()
				.uri("/candidate/{id}", 3)
				.exchange()
				.expectStatus().is2xxSuccessful()
				.expectBody()
				.jsonPath("$.name").isNotEmpty()
				.jsonPath("$.recommendedJobs").isEmpty()
				.returnResult()
				.toString();

		System.out.println(result);
	}

	@Test
	void testPostCandidate() {
		String result = webTestClient
				.post()
				.uri("/candidate")
				.bodyValue(new CandidateDto("5", "ww", Set.of("java", "docker")))
				.exchange()
				.expectStatus().is2xxSuccessful()
				.expectBody()
				.jsonPath("$.id").isNotEmpty()
				.jsonPath("$.name").isEqualTo("ww")
				.jsonPath("$.skills").isArray()
				.returnResult()
				.toString();

		System.out.println(result);
	}
}
