FROM openjdk:8-jdk-alpine
MAINTAINER hanifk2017
COPY target/WaracleCakeManager-0.0.1-SNAPSHOT.jar WaracleCakeManager-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/WaracleCakeManager-0.0.1-SNAPSHOT.jar"]
