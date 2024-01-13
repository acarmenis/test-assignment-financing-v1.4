# FROM As the base for our image, we’ll take the Java-enabled Alpine Linux created in the previous section
FROM openjdk:17-jdk-alpine
ARG JAR_FILE=./target/*.jar
# COPY We let Docker copy our jar file into the image.
COPY ${JAR_FILE} app.jar
# This will be the executable to start when the container is booting.
# We must define them as JSON-Array because we’ll use an ENTRYPOINT in combination with a CMD for some application arguments.
ENTRYPOINT ["java","-jar","/app.jar"]