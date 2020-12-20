FROM adoptopenjdk:11-jre-hotspot
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} marvel.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/marvel.jar"]