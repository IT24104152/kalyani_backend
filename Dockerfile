# ===== Build stage =====
FROM maven:3.8.3-openjdk-24 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# ===== Package stage =====
FROM eclipse-temurin:24-jdk
WORKDIR /app
COPY --from=build /app/target/CODEXA-backend-0.0.1-SNAPSHOT.jar demo.jar
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "demo.jar"]
