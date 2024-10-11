FROM openjdk:17-alpine
LABEL maintainer="github/Lukas22D"
COPY target/ecomerce-api-0.0.1-SNAPSHOT.jar /app/
WORKDIR /app
ENTRYPOINT ["java", "-jar", "ecomerce-api-0.0.1-SNAPSHOT.jar"]
