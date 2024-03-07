FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/ferreteria-0.0.1.jar
COPY ${JAR_FILE} app_corralon.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_corralon.jar"]