FROM openjdk:21
ARG JAR_FILE=/target/*SNAPSHOT.jar
COPY ${JAR_FILE} config-server.jar

EXPOSE 8888
ENTRYPOINT ["java","-jar","config-server.jar"]