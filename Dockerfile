
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /APP


COPY ./pom.xml /APP
COPY ./.mvn  /APP/.mvn
COPY ./mvnw /APP/mvnw



RUN ./mvnw dependency:go-offline

COPY ./src /APP/src
RUN ./mvnw clean install -DskipTests


EXPOSE 8080


ENTRYPOINT ["java", "-jar", "/APP/target/Coworkingmanagment-0.0.1-SNAPSHOT.jar"]
