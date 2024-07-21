FROM maven:3.8.3-openjdk-17-slim AS build

WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package

# Recommended by springboot team: https://spring.io/quickstart
FROM bellsoft/liberica-openjdk-alpine:17-cds

WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]