FROM openjdk:21-jdk-slim AS builder

RUN apt-get update && apt-get install -y bash

WORKDIR /app

COPY gradlew gradlew
COPY gradle gradle
COPY build.gradle settings.gradle /app/
COPY src /app/src

RUN chmod +x gradlew

RUN ./gradlew clean build

FROM openjdk:21-jdk-slim

WORKDIR /app

COPY --from=builder /app/build/libs/ramengo-api-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
