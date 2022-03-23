FROM openjdk:11

WORKDIR /app/server

COPY pom.xml mvnw ./
COPY .mvn .mvn

RUN chmod +x mvnw

RUN ./mvnw dependency:go-offline

COPY src src

RUN ./mvnw compile

CMD ./mvnw spring-boot:run
