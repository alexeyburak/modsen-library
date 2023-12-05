FROM openjdk:21
ARG JAR_FILE=/target/*SNAPSHOT.jar
COPY ${JAR_FILE} discovery.jar

EXPOSE 8761
ENTRYPOINT ["java","-jar","discovery.jar"]