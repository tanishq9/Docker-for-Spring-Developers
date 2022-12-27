package com.example.candidateservice;

import java.io.File;
import org.junit.ClassRule;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class BaseTest {

	private static final int MONGO_PORT = 27017;
	private static final String MONGO = "db";
	private static final String MONGO_URI = "mongodb://candidate_user:candidate_password@%s:%s/candidatedb";
	private static final int MOCK_SERVER_PORT = 1080;
	private static final String MOCK_SERVER = "job-service-mock";
	private static final String MOCK_SERVER_URI = "http://%s:%s/job/";

	@ClassRule
	private static final DockerComposeContainer<?> composeContainer =
			new DockerComposeContainer<>(new File("docker-compose.yaml")); // Each required port needs to be explicitly exposed.

	@DynamicPropertySource
	static void mongoProperties(DynamicPropertyRegistry registry) {
		composeContainer
				.withEnv("HOST_PORT", "0") // 0 for random port
				.withExposedService(MONGO, MONGO_PORT, Wait.forListeningPort()) // Waiting for exposed port to start listening, more here: https://www.testcontainers.org/modules/docker_compose/
				.withExposedService(MOCK_SERVER, MOCK_SERVER_PORT, Wait.forListeningPort()) // Waiting for exposed port to start listening, more here: https://www.testcontainers.org/modules/docker_compose/
				.start();

		var mongoPort = composeContainer.getServicePort(MONGO, MONGO_PORT); // to get mapped port
		var mongoHost = composeContainer.getServiceHost(MONGO, MONGO_PORT);
		var mockServerPort = composeContainer.getServicePort(MOCK_SERVER, MOCK_SERVER_PORT); // to get mapped port
		var mockServerHost = composeContainer.getServiceHost(MOCK_SERVER, MOCK_SERVER_PORT);

		System.out.println("Mongo Host: " + mongoPort + ", Mongo Port:" + mongoHost);

		registry.add("spring.data.mongodb.uri",
				() -> String.format(MONGO_URI, mongoHost, mongoPort));

		registry.add("job.service.url",
				() -> String.format(MOCK_SERVER_URI, mockServerHost, mockServerPort));
	}
}
