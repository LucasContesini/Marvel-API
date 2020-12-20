FROM adoptopenjdk:11-jre-hotspot
EXPOSE 9190
ADD /target/marvel-0.0.1-SNAPSHOT.jar marvel-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "marvel-0.0.1-SNAPSHOT.jar"]