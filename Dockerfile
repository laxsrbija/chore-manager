FROM eclipse-temurin:17

WORKDIR /opt/chore-manager
COPY chores-backend/target/chore-manager.jar .
COPY chores-backend/src/main/resources/application-dev.properties resources/application.properties

EXPOSE 8080
CMD ["java", "-jar", "chore-manager.jar", "--spring.config.location=file:./resources/application.properties"]