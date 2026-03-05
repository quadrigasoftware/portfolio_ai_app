# Stage 1: Build environment
FROM eclipse-temurin:21-jdk AS build
WORKDIR /home/gradle/project

# This Dockerfile expects to be run with the parent directory as context:
# docker build -f portfolio_ai_app/Dockerfile .
COPY portfolio_ai_app ./portfolio_ai_app
COPY type_safe_shared ./type_safe_shared

WORKDIR /home/gradle/project/portfolio_ai_app

# Build the project (includes web wasm build and server fat jar)
RUN ./gradlew :server:buildFatJar --no-daemon

# Stage 2: Runtime environment
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Copy the fat jar from the build stage
COPY --from=build /home/gradle/project/portfolio_ai_app/server/build/libs/server-all.jar /app/server.jar

# Cloud Run defaults to port 8080
EXPOSE 8080

# Run the server
CMD ["java", "-jar", "/app/server.jar"]
