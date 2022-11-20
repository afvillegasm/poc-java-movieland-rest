FROM eclipse-temurin:11
COPY target/*.jar poc-java-movieland-rest.jar
ENTRYPOINT ["java","-jar","/poc-java-movieland-rest.jar"]