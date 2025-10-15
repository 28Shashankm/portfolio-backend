# Step 1: Use an official OpenJDK 21 image
FROM openjdk:21-jdk-slim

# Step 2: Set the working directory
WORKDIR /app

# Step 3: Copy the jar file from target folder to the container
COPY target/backend-0.0.1-SNAPSHOT.jar app.jar

# Step 4: Expose the port Spring Boot runs on
EXPOSE 8080

# Step 5: Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
