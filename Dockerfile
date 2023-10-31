FROM maven:3.9-eclipse-temurin-17 as build

WORKDIR /tmp
COPY . .

RUN mvn -B clean package

FROM eclipse-temurin:21.0.1_12-jre

WORKDIR /opt/chore-manager
COPY --from=build /tmp/chore-manager-backend/chore-manager-application/target/chore-manager.jar .
COPY chore-manager-backend/chore-manager-application/src/main/resources/application-dev.properties resources/application.properties

EXPOSE 8080
CMD ["java", "-jar", "chore-manager.jar", "--spring.config.location=file:./resources/application.properties"]