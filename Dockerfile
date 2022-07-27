FROM openjdk:11

COPY target/users-project.jar users-project.jar

ENTRYPOINT  ["java", "-jar", "/users-project.jar"]