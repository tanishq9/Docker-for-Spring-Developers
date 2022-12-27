package com.example.jobservice;

import com.example.jobservice.dto.JobDto;
import com.example.jobservice.generic.BaseTest;
import com.example.jobservice.generic.BaseTestDockerCompose;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest // will create all beans in main package, needed for IT
@AutoConfigureWebTestClient
class JobServiceIT extends BaseTestDockerCompose {
// class JobServiceIT extends BaseTest {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	public void allJobsTest() {
		this.webTestClient.
				get()
				.uri("/job/all")
				.exchange()
				.expectStatus().is2xxSuccessful()
				.expectBody()
				.consumeWith(e -> System.out.println(new String(e.getResponseBody())))
				.jsonPath("$").isNotEmpty();
	}

	@Test
	public void searchJobsTest() {
		this.webTestClient
				.get()
				.uri("/job/search?skills=java")
				.exchange()
				.expectStatus().is2xxSuccessful()
				.expectBody()
				.jsonPath("$.size()").isEqualTo(3);
	}

	@Test
	public void searchJobsTest2() {
		this.webTestClient
				.get()
				.uri("/job/search?skills=project")
				.exchange()
				.expectStatus().is2xxSuccessful()
				.expectBody()
				.jsonPath("$.size()").isEqualTo(1);
	}

	@Test
	public void postJobTest() {
		var dto = JobDto.create(
				null,
				"k8s engineer",
				"google",
				Set.of("k8s"),
				200000,
				true
		);

		this.webTestClient
				.post()
				.uri("/job")
				.bodyValue(dto)
				.exchange()
				.expectStatus().is2xxSuccessful()
				.expectBody()
				.jsonPath("$.id").isNotEmpty()
				.jsonPath("$.description").isEqualTo("k8s engineer");
	}
}
