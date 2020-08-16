
FROM openjdk:jre-14.0.1-alpine as builder
MAINTAINER  YordanPetrov <y.petrov@ue-varna.bg>
WORKDIR application
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract

FROM openjdk:jre-14.0.1-alpine
WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/resources/ ./
COPY --from=builder application/application/ ./
EXPOSE 8000
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]