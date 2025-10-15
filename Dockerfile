--- STAGE 1: BUILD THE APPLICATION ---
Use a Maven/JDK image as the builder stage
FROM maven:3.9.5-openjdk-21 AS builder

Set the working directory inside the container
WORKDIR /app

Copy the Maven wrapper files (mvnw, .mvn) and pom.xml
This step is crucial for caching if only source code changes
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./

Download dependencies (only if pom.xml changes)
RUN mvn dependency:go-offline

Copy the source code
COPY src/ ./src/

Package the application. This creates the JAR file in the 'target' directory.
RUN mvn clean package -DskipTests

--- STAGE 2: CREATE THE FINAL SMALL IMAGE ---
Use a lightweight JDK image for the final running application
FROM openjdk:21-jdk-slim

Set the working directory
WORKDIR /app

The environment variable PORT is crucial for Render's networking
ENV PORT=8080
EXPOSE 8080

Copy the built JAR file from the 'builder' stage into the final image
NOTE: The JAR name below is derived from your original Dockerfile and pom.xml.
If your final JAR name is different, you must update it here!
COPY --from=builder /app/target/portfolio-backend-0.0.1-SNAPSHOT.jar app.jar

Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
