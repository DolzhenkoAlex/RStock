#stage 1
#Start with a base image containing Java runtime
# Базовый образ, содержащий среду Java времени выполнения
FROM eclipse-temurin:17-jdk-alpine as build

# Add Maintainer Info
# Добавить информацию о владельце
LABEL maintainer="Alex Dolzhenko <doljenkoalex@gmail.com>"

VOLUME /tmp

# The application's jar file
# Файл jar приложения
ARG JAR_FILE

# Add the application's jar to the container
# Добавить файл jar приложения в контейнер с именем app.jar
COPY ${JAR_FILE} app.jar

#EXPOSE 8080

#execute the application
# запустить приложение
ENTRYPOINT ["java","-jar","/app.jar"]
