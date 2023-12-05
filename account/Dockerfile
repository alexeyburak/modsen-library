FROM openjdk:21
ARG JAR_FILE=/target/*SNAPSHOT.jar
COPY ${JAR_FILE} account.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","account.jar"]