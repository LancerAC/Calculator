FROM openjdk:17

COPY app.jar MyApp.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "MyApp.jar"]