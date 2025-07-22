#stage1: build
#Build with Maven image to build the app (backend)
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app
#move project files to container
COPY pom.xml .
COPY src ./src
#Build jar file, usually testing happens here
RUN mvn clean package -DskipTests

#stage 2: run
#Use lightweight JDK image to run the app (frontend)
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
#takes generated jar from build stage
COPY --from=build /app/target/*.jar app.jar
#port for HTTP access
EXPOSE 8080
#command used to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]