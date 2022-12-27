- Docker is used for DB, mocking dependent service and integeration tesing (via testcontainers) in the job and candidate service repositories.

- Execute the below commands in the services for testing -
```
gradle clean build
docker compose up --build
```

