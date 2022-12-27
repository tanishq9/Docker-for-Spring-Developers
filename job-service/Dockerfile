FROM bellsoft/liberica-openjdk-alpine:17.0.3

WORKDIR /usr/share/app

COPY build/libs/job-service-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]

# docker build -t job-service . --> Run this command in project root directory
# Before executing above, make sure jar is present