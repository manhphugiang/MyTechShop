# Use an official OpenJDK 17 runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the Spring Boot jar file to the container
COPY target/MyTechShop-0.0.1-SNAPSHOT.jar.original app.jar

# Expose the port that your Spring Boot app runs on
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
