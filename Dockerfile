FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/*.jar airport-info-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/airport-info-service-0.0.1-SNAPSHOT.jar"]