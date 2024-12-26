#!/bin/bash

# Step 1: Start Docker Compose services
echo "Starting Docker Compose services..."
docker-compose up -d

# Check if Docker Compose command was successful
if [ $? -eq 0 ]; then
    echo "Docker Compose services started successfully."
else
    echo "Failed to start Docker Compose services."
    exit 1
fi

# Step 2: Build the project using Gradle
echo "Building the project with Gradle..."
./gradlew build

# Check if the Gradle build was successful
if [ $? -eq 0 ]; then
    echo "Gradle build completed successfully."
else
    echo "Gradle build failed."
    exit 1
fi

# Step 3: Run the Spring Boot application using Gradle
echo "Starting the Spring Boot application..."
./gradlew bootRun

# Check if the Spring Boot application started successfully
if [ $? -eq 0 ]; then
    echo "Spring Boot application started successfully."
else
    echo "Failed to start Spring Boot application."
    exit 1
fi