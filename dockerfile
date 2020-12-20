FROM adoptopenjdk:11-jre-hotspot
EXPOSE 8081
ADD /target/marvel-0.0.1-SNAPSHOT.jar marvel-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "marvel-0.0.1-SNAPSHOT.jar"]