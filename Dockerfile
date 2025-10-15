--- STAGE 1: BUILD THE APPLICATION ---
CRITICAL FIX: Changed the base image tag to 'maven:21-jdk' which is a standard, available image
FROM maven:21-jdk AS builder

Set the working directory inside the container
WORKDIR /app

Copy the Maven wrapper files (mvnw, .mvn) and pom.xml
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./

Add execute permission to the Maven wrapper
RUN chmod +x mvnw

Download dependencies (only if pom.xml changes)
RUN mvn dependency:go-offline

Copy the source code
COPY src/ ./src/

Package the application. This creates the JAR file in the 'target' directory.
RUN mvn clean package -DskipTests

--- STAGE 2: CREATE THE FINAL SMALL IMAGE ---
FROM openjdk:21-jdk-slim
WORKDIR /app
ENV PORT=8080
EXPOSE 8080
COPY --from=builder /app/target/app.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
