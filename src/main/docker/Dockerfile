FROM adoptopenjdk/openjdk11:alpine-jre
MAINTAINER Luca Saletta Sillero <luca_sillero@hotmail.com>

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]