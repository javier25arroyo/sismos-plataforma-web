# Stage 1: Build
FROM eclipse-temurin:21-jdk-jammy AS build
WORKDIR /app

# Copy gradle wrapper and configuration files
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# Grant execution permission for the gradle wrapper
RUN chmod +x gradlew

# Download dependencies (cache layer)
RUN ./gradlew dependencies --no-daemon

# Copy source code
COPY src src

# Build the application, excluding tests to speed up the process
# We use bootJar to ensure we get the executable jar
RUN ./gradlew bootJar -x test --no-daemon

# Stage 2: Run
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Metadata
LABEL maintainer="ICE Sismo Platform"
LABEL description="Backend for Sismo Platform"

# Create a non-root user for security
RUN addgroup --system spring && adduser --system spring --ingroup spring
USER spring:spring

# Copy the built jar from the build stage
# Using wildcard to be flexible with versioning, but specific to the bootJar output
COPY --from=build /app/build/libs/*.jar app.jar

# Expose the default Spring Boot port
EXPOSE 8080

# Environment variables with defaults (can be overridden)
ENV SPRING_PROFILES_ACTIVE=prod
ENV SPRING_DATASOURCE_URL=jdbc:sqlserver://db:1433;databaseName=SismoDB;encrypt=true;trustServerCertificate=true
ENV SPRING_DATASOURCE_USERNAME=sa
ENV SPRING_DATASOURCE_PASSWORD=YourStrong@Passw0rd

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
