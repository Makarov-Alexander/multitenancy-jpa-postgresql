FROM maven:3.6.0-jdk-8 as builder

COPY src src
COPY pom.xml pom.xml

RUN mvn package

FROM openjdk:8-jre-alpine as runner

COPY --from=builder /target/multitenancy-jpa-postgresql-*.jar multitenancy.jar
COPY /conf/application.docker.properties conf/application.properties
COPY /conf/liquibase.docker.properties liquibase.properties

EXPOSE 8080
ENTRYPOINT exec java -jar multitenancy.jar -Dliquibase.propertyFile=liquibase.properties
