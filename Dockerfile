
FROM amazoncorretto:11-alpine-jdk
MAINTAINER Carlos
COPY target/Carlos-0.0.1-SNAPSHOT.jar  carlos-app.jar
ENTRYPOINT ["java","-jar","/carlos-app.jar"]

