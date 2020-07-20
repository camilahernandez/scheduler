FROM openjdk:12-alpine
EXPOSE 8080
ARG WAR_FILE=scheduler-1.0.war
ADD ${WAR_FILE} app.war
CMD ["java", "-jar", "-Dspring.profiles.active=default", "app.war"]