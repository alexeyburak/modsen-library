FROM openjdk:21
ARG JAR_FILE=/target/*SNAPSHOT.jar
COPY ${JAR_FILE} book.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","book.jar"]