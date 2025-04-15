FROM openjdk:8-jdk-slim

WORKDIR /app

COPY build/libs/Tgbot-fat-1.0-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
