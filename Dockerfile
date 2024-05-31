FROM openjdk:21-jdk-slim AS builder

RUN apt-get update && apt-get install -y bash dos2unix

WORKDIR /app

COPY . .

RUN if [ ! -f ./gradlew ]; then echo "gradlew not found"; exit 1; fi

RUN dos2unix ./gradlew

RUN chmod +x ./gradlew

RUN ./gradlew clean build -x test

FROM openjdk:21-jdk-slim

WORKDIR /app

COPY --from=builder /app/build/libs/ramengo-api-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
