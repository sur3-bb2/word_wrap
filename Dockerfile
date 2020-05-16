FROM openjdk:8-jdk-alpine
WORKDIR /usr/football
COPY ./build/libs/app.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","./app.jar"]