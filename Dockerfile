Dockerfile for Spring Boot deployment on Render
STAGE 1: BUILD THE APPLICATION
FROM maven:21-jdk AS builder

Set the working directory inside the container
WORKDIR /app

Copy the Maven wrapper files (mvnw, .mvn) and pom.xml
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./

Add execute permission to the Maven wrapper
RUN chmod +x mvnw

Download dependencies
RUN mvn dependency:go-offline

Copy the source code
COPY src/ ./src/

Package the application (creates app.jar in target folder)
RUN mvn clean package -DskipTests

STAGE 2: CREATE THE FINAL SMALL IMAGE
FROM openjdk:21-jdk-slim
WORKDIR /app

Environment variable required by Render for PORT
ENV PORT=8080
EXPOSE 8080

Copy the built JAR from the builder stage
COPY --from=builder /app/target/app.jar app.jar

Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
