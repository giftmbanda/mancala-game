# Build stage
# Use Maven 3.8.1 and OpenJDK 17 slim image as a base image
FROM maven:3.8.1-openjdk-17-slim AS build

WORKDIR /usr/src/app

COPY . .

## Run Maven build commands to compile and package the application, while skipping tests
RUN mvn clean install
#RUN mvn clean install -DskipTests

# Package stage
FROM openjdk:17-jdk-slim

# Copy the packaged jar file from the build stage to our Docker context
COPY --from=build /usr/src/app/target/*.jar /app.jar

ENTRYPOINT ["java","-jar","/app.jar"]