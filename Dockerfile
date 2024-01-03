#stage 1
#Start with a base image containing Java runtime
# Базовый образ, содержащий среду Java времени выполнения
FROM eclipse-temurin:17-jdk-alpine as build

# Add Maintainer Info
# Добавить информацию о владельце
LABEL maintainer="Alex Dolzhenko <doljenkoalex@gmail.com>"

# The application's jar file
# Файл jar приложения
ARG JAR_FILE

# Add the application's jar to the container
# Добавить файл jar приложения в контейнер
COPY ${JAR_FILE} app.jar

#unpackage jar file
# Распаковывает app.jar, скопированный ранее,в файловую систему image
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf /app.jar)

#stage 2
#Same Java runtime
FROM eclipse-temurin:17-jdk-alpine

#Add volume pointing to /tmp
VOLUME /tmp

#Copy unpackaged application to new container
ARG DEPENDENCY=/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

#execute the application
ENTRYPOINT ["java","-cp","app:app/lib/*","com.dolsoft.licenses.LicensesApplication"]
