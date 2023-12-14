
FROM openjdk:17-alpine
WORKDIR /app
COPY target/*.jar /app/bankapp.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "bankapp.jar"]