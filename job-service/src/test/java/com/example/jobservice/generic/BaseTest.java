package com.example.jobservice.generic;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
public abstract class BaseTest {

	private static final int MONGO_PORT = 27017;
	private static final String INIT_JS = "/docker-entrypoint-initdb.d/init.js";
	private static final String MONGO_URI_FORMAT = "mongodb://job_user:job_password@%s:%s/jobdb";

	@Container
	private static final GenericContainer<?> mongo
			= new GenericContainer<>(DockerImageName.parse("mongo"))
			.withExposedPorts(MONGO_PORT) // Each required port needs to be explicitly exposed.
			.withClasspathResourceMapping("data/job-init.js", INIT_JS, BindMode.READ_ONLY)
			.waitingFor(Wait.forListeningPort());

	@DynamicPropertySource // to override some application.properties
	static void mongoProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
		dynamicPropertyRegistry.add("spring.data.mongodb.uri",
				() -> String.format(MONGO_URI_FORMAT, mongo.getHost(), mongo.getMappedPort(MONGO_PORT)));
	}
}
