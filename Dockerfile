FROM leonardorocha1990/raspberry-pi-64-alpine-jdk11:1.0.0-SNAPSHOT
RUN apk add --no-cache bash
ARG JAR_FILE=some.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]