FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8080
COPY target/*.jar movie-catalog.jar
ENTRYPOINT ["java","-jar","/movie-catalog.jar"]