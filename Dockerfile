# Estágio de construção com Maven e JDK 17
FROM maven:3.8-openjdk-17 AS build
WORKDIR /usr/src/app
COPY . .
RUN mvn clean package

# Estágio final com JDK 17
FROM eclipse-temurin:17-jdk
WORKDIR /usr/app
COPY --from=build /usr/src/app/target/service-email-spring-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
