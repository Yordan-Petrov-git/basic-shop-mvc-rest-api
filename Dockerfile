FROM adoptopenjdk/openjdk14:jre-14.0.2_12-alpine as builder
#FROM openjdk:14.0.1-jdk as builder
MAINTAINER  YordanPetrov <y.petrov@ue-varna.bg>
HEALTHCHECK --interval=5m --timeout=3s CMD curl -f http://localhost:8000/actuator/health/
WORKDIR application
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract
FROM adoptopenjdk/openjdk14:jre-14.0.2_12-alpine
WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
