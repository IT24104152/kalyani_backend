# ===== Build stage =====
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# ===== Package stage =====
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=build /app/target/CODEXA-backend-0.0.1-SNAPSHOT.jar demo.jar
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "demo.jar"]
