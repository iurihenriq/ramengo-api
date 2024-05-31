FROM ubuntu:latest AS builder

WORKDIR /app

COPY build.gradle settings.gradle /app/

COPY src /app/src

RUN ./gradlew clean build

FROM openjdk:21-jdk-slim

WORKDIR /app

COPY --from=builder /app/build/libs/ramengo-api-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
