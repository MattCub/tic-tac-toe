
FROM maven:3.9.1-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY boot/pom.xml boot/
COPY application/pom.xml application/
COPY api/pom.xml api/
COPY domain/pom.xml domain/
COPY infrastructure/pom.xml infrastructure/
COPY boot/src boot/src/
COPY application/src application/src/
COPY api/src api/src/
COPY domain/src domain/src/
COPY infrastructure/src infrastructure/src/
RUN mvn clean install -DskipTests
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

COPY --from=build /app/boot/target/boot-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
