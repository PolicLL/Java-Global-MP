# Use an official OpenJDK runtime as the base image for building
FROM openjdk:17-jdk-slim AS builder

# Install Maven
RUN apt-get update && apt-get install -y maven

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml and download the dependencies to leverage Docker cache
COPY pom.xml ./
RUN mvn dependency:go-offline -B

# Copy the entire application source code
COPY src ./src

# Build the application with Maven
RUN mvn clean package -DskipTests

# Use a smaller OpenJDK-based image to run the Spring Boot application
FROM openjdk:17-oracle

# Set the working directory inside the container
WORKDIR /app

# Copy the jar file from the build stage
COPY --from=builder /app/target/micro-visualizer-0.0.1-SNAPSHOT.jar /app/micro-visualizer-0.0.1-SNAPSHOT.jar

# Expose the port on which the application will run
EXPOSE 8081

# Command to run the application
ENTRYPOINT ["java", "-jar", "/app/micro-visualizer-0.0.1-SNAPSHOT.jar"]
