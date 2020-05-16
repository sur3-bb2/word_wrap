FROM openjdk:8-jdk-alpine
WORKDIR /usr/football
#COPY ./build/libs/app.jar app.jar
COPY . .
RUN ./gradlew :bootJar
#COPY ./build/libs/app.jar app.jar
EXPOSE 8081
ENTRYPOINT  ["java", "-jar", "./build/libs/app.jar"]