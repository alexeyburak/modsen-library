FROM openjdk:21
ARG JAR_FILE=/target/*SNAPSHOT.jar
COPY ${JAR_FILE} registration.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","registration.jar"]