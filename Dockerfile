FROM maven:3.8.4-openjdk-17 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean install

# Stage 2: Create a smaller runtime image
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copy the application JAR file into the container at /app
COPY target/bennidemo-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the port that your application will run on
EXPOSE 8080

COPY entrypoint.sh /app/entrypoint.sh

ENTRYPOINT ["/bin/bash", "-c", "/app/entrypoint.sh"]
