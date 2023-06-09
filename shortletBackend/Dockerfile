#FROM openjdk:8-jre
#
#EXPOSE 9000
#
#COPY ./target/shortletbackend.jar shortletbackend.jar
#
#ENTRYPOINT ["java","-jar","/shortletbackend.jar"]
#

#FROM maven:3.8-openjdk-18-slim
#WORKDIR /app
#
#COPY . .
#
#RUN mvn package
#
#ENTRYPOINT mvn spring-boot:run

FROM maven:alpine as build

ENV HOME=/usr/app

RUN mkdir -p $HOME

WORKDIR $HOME

ADD . $HOME

RUN mvn package -DskipTests

FROM openjdk:13-jdk-alpine

COPY --from=build /usr/app/target/shortletBackend-0.0.1-SNAPSHOT.jar /app/runner.jar

ENTRYPOINT java -jar /app/runner.jar