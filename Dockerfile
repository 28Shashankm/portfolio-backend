# Dockerfile for Spring Boot deployment on Render

# ---- STAGE 1: BUILD THE APPLICATION ----
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# ---- STAGE 2: RUN THE APPLICATION ----
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
