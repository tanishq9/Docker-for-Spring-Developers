package com.example.jobservice.generic;

import java.io.File;
import org.junit.ClassRule;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class BaseTestDockerCompose {

	private static final int MONGO_PORT = 27017;
	private static final String INIT_JS = "/docker-entrypoint-initdb.d/init.js";
	private static final String MONGO_URI_FORMAT = "mongodb://job_user:job_password@%s:%s/jobdb";

	@ClassRule
	private static final DockerComposeContainer<?> compose
			= new DockerComposeContainer<>(new File("docker-compose.yaml"));

	@DynamicPropertySource // to override some application.properties
	static void mongoProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
		compose
				.withEnv("HOST_PORT", "0") // 0 for random port
				.withExposedService("db", MONGO_PORT, Wait.forListeningPort()) // Waiting for exposed port to start listening, more here: https://www.testcontainers.org/modules/docker_compose/
				.start();

		var port = compose.getServicePort("db", MONGO_PORT); // to get mapped port
		var host = compose.getServiceHost("db", MONGO_PORT);

		dynamicPropertyRegistry.add("spring.data.mongodb.uri",
				() -> String.format(MONGO_URI_FORMAT, host, port));
	}
}
