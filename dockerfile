# base image
FROM maven:3.8.3-openjdk-17 AS build
# info about author
LABEL maintainer="Bartosz Biegajło"
# sets working directory
WORKDIR /app
# we copy pom.xml to the working dir
COPY pom.xml .
# copy the source code
COPY src ./src
# build the JAR file
RUN mvn -f ./pom.xml clean package

# second layer image
FROM openjdk:17.0.1-jdk-slim
# copy the jar built in previous step
COPY --from=build /app/target/pfswcho-zadanie1.jar /pfswcho-zadanie1.jar
# run the jar file
ENTRYPOINT ["java", "-jar", "/pfswcho-zadanie1.jar"]