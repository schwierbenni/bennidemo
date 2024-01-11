# Build the jar file to be abaliable for copy
FROM maven:3.9.6-eclipse-temurin-17 AS build
COPY . /usr/src/app
WORKDIR /usr/src/app
RUN mvn clean package -DskipTests


# Use an official OpenJDK runtime as a parent image
FROM eclipse-temurin:17-jre-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the application JAR file into the container at /app
COPY --from=build /usr/src/app/target/*.jar /app/app.jar
COPY entrypoint.sh /app/entrypoint.sh

# Expose the port that your application will run on
EXPOSE 8080

# Set permissions for the entrypoint script
RUN ["chmod", "+x", "/app/entrypoint.sh"]

ENTRYPOINT ["/bin/bash", "-c", "/app/entrypoint.sh"]